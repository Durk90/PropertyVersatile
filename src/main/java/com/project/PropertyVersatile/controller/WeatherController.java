package com.project.PropertyVersatile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.PropertyVersatile.model.WeatherData;
import com.project.PropertyVersatile.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String showWeatherChart(Model model) {
        // Fetch JSON data from the weather API
        String jsonString = weatherService.getWeatherData();

        // Convert the JSON string to a WeatherData object
        WeatherData weatherData = convertJsonToWeatherData(jsonString);

        // Add the WeatherData object to the model
        model.addAttribute("weatherData", weatherData);

        // Return the Thymeleaf template name
        return "weather";
    }

    private WeatherData convertJsonToWeatherData(String jsonString) {
        // Use Jackson to convert JSON string to WeatherData object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, WeatherData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
