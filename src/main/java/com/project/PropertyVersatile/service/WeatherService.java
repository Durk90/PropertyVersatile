package com.project.PropertyVersatile.service;

// WeatherService.java
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.PropertyVersatile.model.WeatherData;

@Service
public class WeatherService {

    private static final String OPEN_METEO_API_URL = "https://api.open-meteo.com/v1/forecast";

    public WeatherData getWeatherData(double latitude, double longitude) {
        String apiUrl = OPEN_METEO_API_URL +
                "?latitude=" + latitude +
                "&longitude=" + longitude +
                "&current=temperature_2m,wind_speed_10m" +
                "&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, WeatherData.class);
    }
}
