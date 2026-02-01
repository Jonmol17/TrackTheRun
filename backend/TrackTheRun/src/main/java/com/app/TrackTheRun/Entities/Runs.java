package com.app.TrackTheRun.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "runs")
public class Runs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runs_id")
    private Long runsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "distance_km")
    private Float distanceKm;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "effort_level")
    private Integer effortLevel;

    @Lob
    @Column(name = "run_note")
    private String runNote;

    @Column(name = "activity_area")
    private String activityArea;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id")
    private Weather weather;

}
