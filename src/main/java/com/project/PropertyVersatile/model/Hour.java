package com.project.PropertyVersatile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class Hour {
    private String datetime;

    @JsonProperty("datetimeEpoch")
    private long datetimeEpoch;

    private double temp;

    @JsonProperty("feelslike")
    private double feelsLike;

    private double humidity;
    private double dew;
    private double precip;

    @JsonProperty("precipprob")
    private double precipProb;

    private double snow;
    private double snowdepth;

    private List<String> preciptype;

    @JsonProperty("windgust")
    private double windGust;

    @JsonProperty("windspeed")
    private double windSpeed;

    @JsonProperty("winddir")
    private double windDir;

    private double pressure;
    private double visibility;

    @JsonProperty("cloudcover")
    private double cloudCover;

    @JsonProperty("solarradiation")
    private double solarRadiation;

    @JsonProperty("solarenergy")
    private double solarEnergy;

    @JsonProperty("uvindex")
    private double uvIndex;

    private double severeRisk;
    private String conditions;
    private String icon;

    private List<String> stations;

    private String source;

    @JsonProperty("datetimeInstance")
    private String datetimeInstance;

    // Other getters and setters
}
