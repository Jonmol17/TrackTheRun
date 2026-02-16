package com.app.TrackTheRun.Services;

import com.app.TrackTheRun.Dtos.CreateRunDTO;
import com.app.TrackTheRun.Dtos.CreateRunResponseDTO;
import com.app.TrackTheRun.Entities.Runs;
import com.app.TrackTheRun.ExternalAPI.Services.WeatherService;
import com.app.TrackTheRun.Repositories.RunsRepository;
import com.app.TrackTheRun.Repositories.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RunsService {

    private final RunsRepository runsRepository;
    private final WeatherService weatherService;

    public CreateRunResponseDTO createNewRun(CreateRunDTO dto) {

        Runs runs = new Runs();
        runs.setDistanceKm(dto.getDistanceKm());
        runs.setDurationSeconds(dto.getDurationSeconds());
        runs.setStartTime(dto.getStartTime());
        runs.setRunNote(dto.getRunNote());
        runs.setEffortLevel(dto.getEffortLevel());
        runs.setActivityArea(dto.getActivityArea());

        // sparar löpturen
        runsRepository.save(runs);

        // anropar getWeather för väder data
        String dateForWeather = dto.getStartTime().toString();

        weatherService.getWeather(dto.getLat(), dto.getLon(), dateForWeather);

        CreateRunResponseDTO resDTO = new CreateRunResponseDTO();
        resDTO.distanceKm = dto.getDistanceKm();
        resDTO.durationSeconds = dto.getDurationSeconds();
        resDTO.startTime = dto.getStartTime();
        resDTO.runNote = dto.getRunNote();
        resDTO.effortLevel = dto.getEffortLevel();
        resDTO.activityArea = dto.getActivityArea();
        // TODO: Lägg till väderdata till obj...

        return resDTO;
    }
}
