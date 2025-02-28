package ru.shaxowskiy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.models.dto.WeatherResponseDTO;
import ru.shaxowskiy.models.dto.LocationResponseDTO;
import ru.shaxowskiy.services.LocationService;
import ru.shaxowskiy.services.OpenWeatherApiService;

import java.util.List;

@Controller
public class LocationController {

    private final OpenWeatherApiService openWeatherApiService;
    private final LocationService locationService;

    @Autowired
    public LocationController(OpenWeatherApiService locationService, LocationService locationService1) {
        this.openWeatherApiService = locationService;
        this.locationService = locationService1;
    }

    @GetMapping("/weather")
    public String getInfoAboutCity(@RequestParam String city, Model model) throws JsonProcessingException {
        List<LocationResponseDTO> infoByCity = openWeatherApiService.getInfoAboutCityForName(city);
        model.addAttribute("locations", infoByCity);
        return "weather-search";
    }

    @PostMapping("/weather/delete")
    public String deleteInfoAboutCity(@ModelAttribute("location") WeatherResponseDTO weatherResponseDTO){
        locationService.delete(weatherResponseDTO.getCoord().getLat(), weatherResponseDTO.getCoord().getLon());
        return "redirect:/welcome";
    }
}
