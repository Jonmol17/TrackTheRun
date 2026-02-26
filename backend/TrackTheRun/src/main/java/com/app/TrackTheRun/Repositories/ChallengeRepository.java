package com.app.TrackTheRun.Repositories;

import com.app.TrackTheRun.Entities.Challenge;
import com.app.TrackTheRun.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Challenge findByChallengeId(Long challengeId);

    List<Challenge> findChallengeByUser(User user);
}
