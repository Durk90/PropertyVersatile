package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    // Handles GET requests for displaying the weather page
    @GetMapping("/weather")
    public String showWeatherPage() {
        // Returns the name of the view template for rendering the weather page
        return "weather"; 
    }
}
