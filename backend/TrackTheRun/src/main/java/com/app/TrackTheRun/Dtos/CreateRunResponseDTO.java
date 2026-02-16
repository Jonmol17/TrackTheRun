package com.app.TrackTheRun.Dtos;

import java.time.LocalDateTime;

public class CreateRunResponseDTO {

    // runs
    public Float distanceKm;
    public Integer durationSeconds;
    public LocalDateTime startTime;
    public String runNote;
    public Integer effortLevel;
    public String activityArea;
    public LocalDateTime createdAt;

    // weather data
    public Integer avgCelsius;
    public Integer maxCelsius;
    public Integer minCelsius;
    public Integer avgWindKph;
    public String condition;
}
