package ru.shaxowskiy.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("main")
    private Main main;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord {
        private BigDecimal lon;
        private BigDecimal lat;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        private String country;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String main;
        private String description;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private int temperature;

        @JsonProperty("feels_like")
        private int feelsLikeTemperature;

        @JsonProperty("humidity")
        private int humidity;

        @JsonSetter("temp")
        public void setTemperature(double temp) {
            this.temperature = (int) Math.round(temp - 273.15);
        }

        @JsonSetter("feels_like")
        public void setFeelsLikeTemperature(double feelsLike) {
            this.feelsLikeTemperature = (int) Math.round(feelsLike - 273.15);
        }
    }
}