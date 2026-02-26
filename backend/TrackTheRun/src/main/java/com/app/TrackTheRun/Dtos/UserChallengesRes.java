package com.app.TrackTheRun.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserChallengesRes {
    public Long challengeId;
    public String name;
    public String description;
    public Integer distanceGoal;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
}
