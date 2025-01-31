package ru.shaxowskiy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.services.UserService;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        System.out.println("GET-method register with form");
        log.info("GET-method register with form");
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/register";
        }
        System.out.println("POST-method register with form");
        log.info("POST-method with user:{}", user);
        userService.addUser(user);
        return "redirect:login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User foundUser = userService.findByUsername(username);
        if (username.equals(foundUser.getUsername()) && password.equals(foundUser.getPassword())) {
            model.addAttribute("message", "Успешный вход!");
            return "welcome";
        } else {
            model.addAttribute("error", "Неверное имя пользователя или пароль.");
            return "login";
        }
    }
}
