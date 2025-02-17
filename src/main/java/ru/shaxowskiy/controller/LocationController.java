package ru.shaxowskiy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shaxowskiy.models.dto.LocationDTO;
import ru.shaxowskiy.services.OpenWeatherApiService;

@RestController
public class LocationController {

    private final OpenWeatherApiService locationService;

    public LocationController(OpenWeatherApiService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/weather")
    public LocationDTO getInfoAboutCity(@RequestParam String city) throws JsonProcessingException {
        return locationService.getInfoByCity(city);
    }
}
