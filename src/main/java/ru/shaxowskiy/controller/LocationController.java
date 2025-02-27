package ru.shaxowskiy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shaxowskiy.models.dto.WeatherResponseDTO;
import ru.shaxowskiy.models.dto.LocationResponseDTO;
import ru.shaxowskiy.services.LocationService;
import ru.shaxowskiy.services.OpenWeatherApiService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        System.out.println(infoByCity);
        model.addAttribute("locations", infoByCity);
        return "weather-search";
    }
}
