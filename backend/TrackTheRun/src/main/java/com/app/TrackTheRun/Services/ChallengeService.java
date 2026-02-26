package com.app.TrackTheRun.Services;

import com.app.TrackTheRun.Dtos.CreateChallengeDTO;
import com.app.TrackTheRun.Dtos.CreateChallengeResponseDTO;
import com.app.TrackTheRun.Dtos.DeleteChallengeDTO;
import com.app.TrackTheRun.Dtos.UserChallengesRes;
import com.app.TrackTheRun.Entities.Challenge;
import com.app.TrackTheRun.Entities.User;
import com.app.TrackTheRun.Repositories.ChallengeRepository;
import com.app.TrackTheRun.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public CreateChallengeResponseDTO createChallenge(CreateChallengeDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Kunde inte hitta användaren!"));

        LocalDateTime currentDate = LocalDateTime.now();
        if (dto.getStartDate().isBefore(currentDate)) {
           throw new RuntimeException("STARTDATE_FUTURE");
        }

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new RuntimeException(("END_BEFORE_STARTDATE"));
        }

        Challenge challenge = new Challenge();
        challenge.setName(dto.getName());
        challenge.setDescription(dto.getDescription());
        challenge.setDistanceGoal(dto.getDistanceGoal());
        challenge.setStartDate(dto.getStartDate());
        challenge.setEndDate(dto.getEndDate());
        challenge.setUser(user);

        CreateChallengeResponseDTO res = new CreateChallengeResponseDTO();
        res.name = dto.getName();
        res.description = dto.getDescription();
        res.distanceGoal = dto.getDistanceGoal();
        res.startDate = dto.getStartDate();
        res.endDate = dto.getEndDate();

        challengeRepository.save(challenge);

        return res;
    }

    public void deleteChallenge(DeleteChallengeDTO dto) {

        Challenge challenge = challengeRepository.findByChallengeId(dto.id);

        User user = challenge.getUser();
        log.warn("användarens id: {}", user.getUserId());
        log.warn("input user id: {}", dto.userId);

        if (user.getUserId() != dto.userId) {
            throw new RuntimeException("UNAUTHORIZED");
        }

        challengeRepository.deleteById(dto.id);
    }

    public List<UserChallengesRes> getUserCreatedChallenges(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Hittade ingen användare!"));

        List<Challenge> result = challengeRepository.findChallengeByUser(user);
        List<UserChallengesRes> res = result
                .stream()
                .map(r -> new UserChallengesRes(
                        r.getChallengeId(),
                        r.getName(),
                        r.getDescription(),
                        r.getDistanceGoal(),
                        r.getStartDate(),
                        r.getEndDate()
                )).toList();

        return res;
    }
}
