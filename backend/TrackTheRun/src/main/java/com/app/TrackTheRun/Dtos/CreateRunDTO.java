package com.app.TrackTheRun.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRunDTO {

    // userId ska tas bort senare då keycloak finns, ska då hämta genom claims.
    private long userId;
    private Float distanceKm;
    private Integer durationSeconds;
    private String startTime;
    private String runNote;
    private Integer effortLevel;
    private String activityArea;

    // kordinater
    private String lat;
    private String lon;
}
