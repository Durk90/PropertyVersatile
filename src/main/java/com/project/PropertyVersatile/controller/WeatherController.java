package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @GetMapping("/weather")
    public String showForecastChart() {
        return "weather"; // This corresponds to forecast.html
    }

    @GetMapping("/archive")
    public String showArchiveChart() {
        return "weather-archive"; // This corresponds to archive.html
    }
}
