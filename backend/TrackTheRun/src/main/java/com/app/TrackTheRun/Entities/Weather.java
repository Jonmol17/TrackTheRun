package com.app.TrackTheRun.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Long weatherId;

    // Medel
    /*@Column(name = "avg_celsius")
    private Integer avgCelsius;*/

    @Column(name = "temp_c")
    private String temp_c;

    @Column(name = "wind_kph")
    private Double wind_kph;

    // exempel: Clear, Cloudy, Rainy....
    @Column(name = "condition")
    private String condition;

    // Max
    /*@Column(name = "max_celsius")
    private Integer maxCelsius;

    // Minimum
    @Column(name = "min_celsius")
    private Integer minCelsius;

    @Column(name = "avg_wind_speed")
    private Integer maxWindKph;*/


}
