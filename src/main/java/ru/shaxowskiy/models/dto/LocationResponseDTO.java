package ru.shaxowskiy.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponseDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("lat")
    private BigDecimal lat;

    @JsonProperty("lon")
    private BigDecimal lon;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;
}