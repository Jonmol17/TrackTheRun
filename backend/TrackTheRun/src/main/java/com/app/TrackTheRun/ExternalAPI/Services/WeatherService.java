package com.app.TrackTheRun.ExternalAPI.Services;

import com.app.TrackTheRun.ExternalAPI.Dtos.WeatherDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${WEATHER_BASE_URL}")
    private String baseUrl;

    @Value("${WEATHER_API_KEY}")
    private String apiKey;

    public WeatherDTO getWeather(String lat, String lon, LocalDateTime date) {

        String geoLocation = lat + "," + lon;

        String dateApi = date.toLocalDate().toString();
        int hourApi = date.getHour();

        // "Historisk" data.
        String apiUrl = baseUrl +
                "/history.json?" +
                "q=" + geoLocation +
                "&dt=" + dateApi +
                "&hour=" + hourApi +
                "&key=" + apiKey
                ;

        WeatherDTO res;

        // https://www.weatherapi.com/
        try {
            res = restTemplate.getForObject(apiUrl, WeatherDTO.class);

            System.out.println("date: " + dateApi);
            System.out.println("-------------------------------------");
            System.out.println("hour: " + hourApi);
            System.out.println("-------------------------------------");

            System.out.println("Väder data från fetch: ");
            System.out.println("-------------------------------------");
            System.out.println(res);
            System.out.println("-------------------------------------");

        } catch (Exception e) {
            throw new RuntimeException(e + "API Error: " + e.getMessage());
        }

        if (res == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "Gick inte att hämta väderdata, testa igen senare..."
            );
        }

        return res;
    }
}
