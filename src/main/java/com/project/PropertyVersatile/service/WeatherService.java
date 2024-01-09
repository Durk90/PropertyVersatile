package com.project.PropertyVersatile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/retrievebulkdataset" +
            "?key=9SN22472KDT63UN7MLCRYK4B9&taskId=6c9886f7fc7a15c78467b509d4423377&zip=false";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherData() {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
