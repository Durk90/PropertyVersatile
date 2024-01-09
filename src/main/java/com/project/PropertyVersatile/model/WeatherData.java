package com.project.PropertyVersatile.model;

public class WeatherData {
    private CurrentWeather current;
    private HourlyWeather hourly;

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public HourlyWeather getHourly() {
        return hourly;
    }

    public void setHourly(HourlyWeather hourly) {
        this.hourly = hourly;
    }
}
