package com.project.PropertyVersatile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.PropertyVersatile.weather.WeatherData;

@Service
public class WeatherService {
    private final String apiUrl = "https://api.open-meteo.com/v1/forecast";

    public WeatherData getWeather(double latitude, double longitude) {
        String url = apiUrl + "?latitude=" + latitude + "&longitude=" + longitude + "&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";
        return new RestTemplate().getForObject(url, WeatherData.class);
    }
}
