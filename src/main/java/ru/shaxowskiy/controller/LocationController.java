package ru.shaxowskiy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shaxowskiy.models.dto.LocationDTO;
import ru.shaxowskiy.services.LocationService;
import ru.shaxowskiy.services.OpenWeatherApiService;

@Slf4j
@Controller
public class LocationController {

    private final OpenWeatherApiService openWeatherApiService;
    private final LocationService locationService;

    @Autowired
    public LocationController(OpenWeatherApiService openWeatherApiService, LocationService locationService1) {
        this.openWeatherApiService = openWeatherApiService;
        this.locationService = locationService1;
    }

    @GetMapping("/weather")
    public String getInfoAboutCity(@RequestParam String city, Model model) throws JsonProcessingException {
        log.info("Запрос GET успешно обработан /weather");
        LocationDTO infoByCity = openWeatherApiService.getInfoByCity(city);
        model.addAttribute("location", infoByCity);
        model.addAttribute("history", locationService.findAll());
        return "weather-search";
    }

}
