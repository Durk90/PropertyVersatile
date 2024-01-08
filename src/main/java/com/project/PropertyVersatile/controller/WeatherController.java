package com.project.PropertyVersatile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.PropertyVersatile.service.WeatherService;
import com.project.PropertyVersatile.weather.WeatherData;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model) {
        // Assuming Berlin's coordinates for demonstration purposes
        double latitude = 52.52;
        double longitude = 13.41;

        WeatherData weatherData = weatherService.getWeather(latitude, longitude);
        model.addAttribute("weatherData", weatherData);

        return "weather"; // Create a Thymeleaf template for displaying weather information
    }
}
