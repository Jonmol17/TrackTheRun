package com.app.TrackTheRun.ExternalAPI.Controllers;

import com.app.TrackTheRun.ExternalAPI.Services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
}
