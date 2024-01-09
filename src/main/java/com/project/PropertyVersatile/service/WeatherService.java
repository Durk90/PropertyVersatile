package com.project.PropertyVersatile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Ireland" +
            "?unitGroup=metric&key=9SN22472KDT63UN7MLCRYK4B9&contentType=json";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherData() {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
