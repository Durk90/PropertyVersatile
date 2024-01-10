package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherMapController {

    @GetMapping("/weather-map")
    public String showWeatherMap() {
        return "weather-map";
    }
}