package com.project.PropertyVersatile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Day {
    private String datetime;

    @JsonProperty("datetimeEpoch")
    private long datetimeEpoch;

    @JsonProperty("tempmax")
    private double tempMax;

    @JsonProperty("tempmin")
    private double tempMin;

    private double temp;

    @JsonProperty("feelslikemax")
    private double feelsLikeMax;

    @JsonProperty("feelslikemin")
    private double feelsLikeMin;

    @JsonProperty("feelslike")
    private double feelsLike;

    private double dew;
    private double humidity;
    private double precip;

    @JsonProperty("precipprob")
    private double precipProb;

    @JsonProperty("precipcover")
    private double precipCover;

    private List<String> preciptype;
    private double snow;
    private double snowdepth;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double cloudcover;
    private double visibility;

    @JsonProperty("solarradiation")
    private double solarRadiation;

    @JsonProperty("solarenergy")
    private double solarEnergy;

    @JsonProperty("uvindex")
    private double uvIndex;

    @JsonProperty("severerisk")
    private double severeRisk;

    private String sunrise;

    @JsonProperty("sunriseEpoch")
    private long sunriseEpoch;

    private String sunset;

    @JsonProperty("sunsetEpoch")
    private long sunsetEpoch;

    private double moonphase;
    private String conditions;
    private String description;
    private String icon;

    private List<String> stations;

    @JsonProperty("hours")
    private List<Hour> hours;

    @JsonProperty("precipsum")
    private double precipSum;

    @JsonProperty("precipsumnormal")
    private double precipSumNormal;

    @JsonProperty("snowsum")
    private double snowSum;

    @JsonProperty("datetimeInstance")
    private String datetimeInstance;

    // Other getters and setters
}
