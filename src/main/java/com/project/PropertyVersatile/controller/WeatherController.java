package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.PropertyVersatile.service.WeatherService;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String showWeather(Model model) {
        String weatherData = weatherService.getWeatherData();
        model.addAttribute("weatherData", weatherData);
        return "weather";
    }
}
