package ru.shaxowskiy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shaxowskiy.models.dto.LocationDTO;
import ru.shaxowskiy.services.OpenWeatherApiService;

@Controller
public class LocationController {

    private final OpenWeatherApiService locationService;

    public LocationController(OpenWeatherApiService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/weather")
    public String getInfoAboutCity(@RequestParam String city, Model model) throws JsonProcessingException {
        LocationDTO infoByCity = locationService.getInfoByCity(city);
        model.addAttribute("weather", infoByCity);
        return "weather-search";
    }

    @PostMapping("/welcome")
    public String addCardWithWeather() {

        System.out.println("Форма была отправлена!");
        return "redirect:/welcome";
    }
    }
