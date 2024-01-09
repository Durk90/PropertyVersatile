package com.project.PropertyVersatile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private String timestamp;
    private Double temperature_2m;
    private Double relative_humidity_2m;
    private Double precipitation_probability;
    private Double snowfall;
    private Double snow_depth;
    private Double wind_speed_10m;

    // getters and setters
}
