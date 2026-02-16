package com.app.TrackTheRun.ExternalAPI.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDTO {
    private Location location;
    private Forecast forecast;

    @Data
    public static class Location {
        private String name;
        private String country;
    }

    @Data
    public static class Forecast {
        private List<ForecastDay> forecastday;
    }

    @Data
    public static class ForecastDay {
        private String date;

        private List<Hour> hour;
    }

    @Data public static class Hour {
        private String time;
        private Double temp_c;
        private double wind_kph;

        private Condition condition;
    }

    @Data
    public static class Condition {
        private String text;
    }
}
