package com.project.PropertyVersatile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.PropertyVersatile.model.WeatherData;
import com.project.PropertyVersatile.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(Model model) {
        double latitude = 52.52; // Replace with actual latitude
        double longitude = 13.41; // Replace with actual longitude

        WeatherData weatherData = weatherService.getWeatherData(latitude, longitude);

        model.addAttribute("weatherData", weatherData);

        return "weather";
    }
}
