package com.project.PropertyVersatile.model;

import java.util.List;

public class HourlyWeather {
    private List<String> time;
    private List<Double> temperature_2m;
    private List<Double> relative_humidity_2m;
    private List<Double> wind_speed_10m;

    // getters and setters

    public List<String> getTime() {
        return time;
    }

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public List<Double> getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public List<Double> getWind_speed_10m() {
        return wind_speed_10m;
    }
}