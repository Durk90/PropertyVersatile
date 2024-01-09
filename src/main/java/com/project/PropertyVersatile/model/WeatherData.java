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
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(Double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public Double getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(Double relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public Double getPrecipitation_probability() {
        return precipitation_probability;
    }

    public void setPrecipitation_probability(Double precipitation_probability) {
        this.precipitation_probability = precipitation_probability;
    }

    public Double getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(Double snowfall) {
        this.snowfall = snowfall;
    }

    public Double getSnow_depth() {
        return snow_depth;
    }

    public void setSnow_depth(Double snow_depth) {
        this.snow_depth = snow_depth;
    }

    public Double getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(Double wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }
}
