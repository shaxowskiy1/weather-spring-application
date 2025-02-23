package ru.shaxowskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shaxowskiy.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final SessionService sessionService;

    @Autowired
    public WelcomeController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String welcome(HttpServletRequest req, Model model){
        System.out.println(Arrays.toString(req.getCookies()));
        model.addAttribute("user", sessionService.getUserFromSession(req).orElseThrow(null));
        return "welcome";
    }
}
