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

    // weather data
    public String temp_c;
    public Double wind_kph;
    public String condition;
    public String name;
    public String country;
}
