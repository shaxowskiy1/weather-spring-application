package ru.shaxowskiy.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}