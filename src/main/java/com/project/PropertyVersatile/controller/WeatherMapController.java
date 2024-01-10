package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherMapController {

    @GetMapping("/weathermap")
    public String showWeatherMap() {
        return "weathermap";
    }
}