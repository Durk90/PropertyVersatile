package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherMapController {

    // Handles GET requests for displaying the weather map page
    @GetMapping("/weather-map")
    public String showWeatherMap() {
        // Returns the name of the view template for rendering the weather map page
        return "weather-map";
    }
}
