package com.app.TrackTheRun.Dtos;

import com.app.TrackTheRun.Entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateChallengeDTO {

    public String name;
    public String description;
    public Integer distanceGoal;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public Long userId;
}
