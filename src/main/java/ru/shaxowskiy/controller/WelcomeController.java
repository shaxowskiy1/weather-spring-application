package ru.shaxowskiy.controller;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shaxowskiy.exception.UserNotFoundException;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.models.dto.LocationResponseDTO;
import ru.shaxowskiy.services.LocationService;
import ru.shaxowskiy.services.OpenWeatherApiService;
import ru.shaxowskiy.services.SessionService;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final SessionService sessionService;
    private final OpenWeatherApiService openWeatherApiService;
    private LocationService locationService;

    @Autowired
    public WelcomeController(SessionService sessionService, LocationService locationService, OpenWeatherApiService openWeatherApiService) {
        this.sessionService = sessionService;
        this.locationService = locationService;
        this.openWeatherApiService = openWeatherApiService;
    }

    @GetMapping
    public String welcome(HttpServletRequest req, Model model){
        System.out.println("get method welcome");
        User user = sessionService.getUserFromSession(req).orElseThrow(null);
        model.addAttribute("user", user);
        try {
            model.addAttribute("cardWeather", openWeatherApiService.getInfoAboutCityForCoord(locationService.findByUser(user)));
        } catch (JsonProcessingException e) {
            System.out.println("catch get welcome");
            throw new RuntimeException(e);
        }
        return "welcome";
    }

    @PostMapping
    public String addCardWithCity(@ModelAttribute("location") LocationResponseDTO locationResponseDTO,
                                  HttpServletRequest httpServletRequest) {
        User userFromSession = sessionService.getUserFromSession(httpServletRequest).orElseThrow(UserNotFoundException::new);
        locationService.save(locationResponseDTO, userFromSession);
        return "redirect:/welcome";
    }
}
