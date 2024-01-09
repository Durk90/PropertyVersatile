package com.project.PropertyVersatile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherData {
    private double latitude;
    private double longitude;

    @JsonProperty("resolvedAddress")
    private String resolvedAddress;

    @JsonProperty("address")
    private String address;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("tzoffset")
    private int tzoffset;

    @JsonProperty("description")
    private String description;

    @JsonProperty("days")
    private List<Day> days;

    @JsonProperty("currentConditions")
    private CurrentConditions currentConditions;

    // Other getters and setters
}
