package com.app.TrackTheRun.ExternalAPI.Services;

import com.app.TrackTheRun.ExternalAPI.Dtos.WeatherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class WeatherService {

    RestClient restClient = RestClient.create();

    @Value("${WEATHER_BASE_URL}")
    private String baseUrl;

    @Value("${WEATHER_API_KEY}")
    private String apiKey;


    public WeatherDTO getWeather(String lat, String lon, String date) {

        String geoLocation = lat + "," + lon;

        // URL mot "History" data.
        String apiUrl = baseUrl +
                "history.json?key=" + apiKey +
                "&q=" + geoLocation +
                "&dt=" + date;

        WeatherDTO res = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(WeatherDTO.class);

        if (res == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "Gick inte att hämta väderdata, vänligen försök igen senare..."
            );
        }

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedDay = dateTime.toLocalDate().toString();     // yyyy-MM-dd
        String formattedHour = String.valueOf(dateTime.getHour());   // H

        WeatherDTO.ForecastDay day = res.getForecast().getForecastday().stream()
                .filter(d -> d.getDate().equals(formattedDay))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingen data för datumet"));

        WeatherDTO.Hour hour = day.getHour().stream()
                .filter(h -> h.getTime().contains(formattedHour + ":"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingen data för timmen"));

        return res;
    }

}

