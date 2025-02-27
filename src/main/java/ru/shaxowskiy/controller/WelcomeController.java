package ru.shaxowskiy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shaxowskiy.models.dto.LocationDTO;
import ru.shaxowskiy.services.LocationService;
import ru.shaxowskiy.services.SessionService;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final SessionService sessionService;
    private final LocationService locationService;

    @Autowired
    public WelcomeController(SessionService sessionService, LocationService locationService) {
        this.sessionService = sessionService;
        this.locationService = locationService;
    }

    @GetMapping
    public String welcome(HttpServletRequest req, Model model){
        model.addAttribute("user", sessionService.getUserFromSession(req).orElseThrow(null));
        return "welcome";
    }
    @PostMapping("/welcome")
    public String addCardWithWeather(@ModelAttribute("location") LocationDTO location) {
        log.debug("Пришёл объект {}", location);
        log.info("Запрос POST успешно обработан /welcome");
        locationService.save(location);
        return "redirect:/welcome";
    }
}
