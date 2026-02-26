package com.app.TrackTheRun.Dtos;

import java.time.LocalDateTime;

public class CreateChallengeResponseDTO {

    // public Long challengeId;
    public String name;
    public String description;
    public Integer distanceGoal;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
}
