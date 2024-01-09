package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.PropertyVersatile.model.WeatherData;
import com.project.PropertyVersatile.service.WeatherService;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model) {
        double latitude = 52.52;
        double longitude = 13.41;

        WeatherData weatherData = weatherService.getWeatherData(latitude, longitude);
        model.addAttribute("weatherData", weatherData);

        return "weather";
    }
}
