package com.project.PropertyVersatile.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.PropertyVersatile.model.WeatherData;

@Service
public class WeatherService {
    
    @Value("${weather.api.url}")
    private String weatherApiUrl;

    public WeatherData getWeatherData(double latitude, double longitude) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(weatherApiUrl)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m,rain,showers,snowfall")
                .queryParam("hourly", "temperature_2m,relative_humidity_2m,precipitation_probability,snowfall,snow_depth,wind_speed_10m")
                .queryParam("timezone", "Europe/London");

        String apiUrl = builder.toUriString();

        // Make an HTTP request to the weather API
        RestTemplate restTemplate = new RestTemplate();
        WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);

        return weatherData;
    }
}

