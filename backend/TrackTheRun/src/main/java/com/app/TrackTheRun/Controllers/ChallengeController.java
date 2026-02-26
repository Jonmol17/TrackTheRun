package com.app.TrackTheRun.Controllers;

import com.app.TrackTheRun.Dtos.CreateChallengeDTO;
import com.app.TrackTheRun.Dtos.CreateChallengeResponseDTO;
import com.app.TrackTheRun.Dtos.DeleteChallengeDTO;
import com.app.TrackTheRun.Dtos.UserChallengesRes;
import com.app.TrackTheRun.Services.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody CreateChallengeDTO dto) {

        CreateChallengeResponseDTO createdChallenge = null;

        try {
            createdChallenge = challengeService.createChallenge(dto);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("STARTDATE_FUTURE")) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Start datumet måste vara i framtiden");
            } else if (e.getMessage().equals("END_BEFORE_STARTDATE")) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Slut datumet måste vara efter start datumet!");
            }
            return ResponseEntity.internalServerError().body("Något gick fel!");
        }
        return ResponseEntity.ok(createdChallenge);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteChallenge(@RequestBody DeleteChallengeDTO dto) {
        try {
            challengeService.deleteChallenge(dto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("UNAUTHORIZED")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/allUserChallenges/{userId}")
    public List<UserChallengesRes> getUserCreatedChallenges(@PathVariable Long userId) {
        return challengeService.getUserCreatedChallenges(userId);
    }
}
