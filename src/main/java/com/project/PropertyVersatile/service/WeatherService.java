package com.project.PropertyVersatile.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.PropertyVersatile.model.WeatherData;

@Service
public class WeatherService {

    private final String OPEN_METEO_FORECAST_URL = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,precipitation,rain,snowfall,wind_speed_10m";
    private final String OPEN_METEO_ARCHIVE_URL = "https://archive-api.open-meteo.com/v1/archive?latitude=52.52&longitude=13.41&start_date=2023-12-25&end_date=2024-01-08&hourly=temperature_2m,precipitation,rain,snowfall,wind_speed_10m&timezone=Europe%2FLondon";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<WeatherData> getForecastData() {
        return restTemplate.getForEntity(OPEN_METEO_FORECAST_URL, WeatherData.class);
    }

    public ResponseEntity<WeatherData> getArchiveData() {
        return restTemplate.getForEntity(OPEN_METEO_ARCHIVE_URL, WeatherData.class);
    }
}
