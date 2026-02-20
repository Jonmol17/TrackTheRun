package com.app.TrackTheRun.Services;

import com.app.TrackTheRun.Dtos.CreateRunDTO;
import com.app.TrackTheRun.Dtos.CreateRunResponseDTO;
import com.app.TrackTheRun.Entities.Runs;
import com.app.TrackTheRun.Entities.User;
import com.app.TrackTheRun.Entities.Weather;
import com.app.TrackTheRun.ExternalAPI.Services.WeatherService;
import com.app.TrackTheRun.Repositories.RunsRepository;
import com.app.TrackTheRun.Repositories.UserRepository;
import com.app.TrackTheRun.Repositories.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class RunsService {

    private final RunsRepository runsRepository;
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;

    public CreateRunResponseDTO createNewRun(CreateRunDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Kunde inte hitta användaren!") );

        if (dto.getEffortLevel() < 1 || dto.getEffortLevel() > 10) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Upplevd ansträngning måste vara mellan 1 - 10");
        }

        var convertStartTime = LocalDateTime.parse(dto.getStartTime());

        // löptur
        Runs runs = new Runs();
        runs.setUser(user);
        runs.setDistanceKm(dto.getDistanceKm());
        runs.setDurationSeconds(dto.getDurationSeconds());
        runs.setStartTime(convertStartTime);
        runs.setRunNote(dto.getRunNote());
        runs.setEffortLevel(dto.getEffortLevel());
        runs.setActivityArea(dto.getActivityArea());

        // sparar löpturen
        runsRepository.save(runs);

        // Sätter response dto obj
        CreateRunResponseDTO resDTO = new CreateRunResponseDTO();
        resDTO.distanceKm = dto.getDistanceKm();
        resDTO.durationSeconds = dto.getDurationSeconds();
        resDTO.startTime = convertStartTime;
        resDTO.runNote = dto.getRunNote();
        resDTO.effortLevel = dto.getEffortLevel();
        resDTO.activityArea = dto.getActivityArea();

        // anropar getWeather för väderdata om lat och lon skickas med.
        if (!dto.getLat().isEmpty() && !dto.getLon().isEmpty()) {
            var whData = weatherService.getWeather(dto.getLat(), dto.getLon(), convertStartTime);

            Weather weather = new Weather();
            // Om väderdata inte är null, sätter C, vind kph och condition text och land
            if (whData != null) {
                String temp_c = whData.getForecast().getForecastday().getFirst().getHour().getFirst().getTemp_c();
                Double wind_kph = whData.getForecast().getForecastday().getFirst().getHour().getFirst().getWind_kph();
                String condition_text = whData.getForecast().getForecastday().getFirst().getHour().getFirst().getCondition().getText();
                String country = whData.getLocation().getCountry();

                weather.setTemp_c(temp_c);
                weather.setWind_kph(wind_kph);
                weather.setCondition(condition_text);

                // sparar väder data
                weatherRepository.save(weather);

                // sätter väder data till response
                resDTO.temp_c = temp_c;
                resDTO.wind_kph = wind_kph;
                resDTO.condition = condition_text;
                resDTO.country = country;
            }
        }

        return resDTO;
    }
}
