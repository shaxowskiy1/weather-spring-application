package ru.shaxowskiy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.services.SessionService;
import ru.shaxowskiy.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public AuthController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        log.info("Запрос GET успешно обработан /register");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/register";
        }
        log.info("Запрос POST успешно обработан /register");
        userService.addUser(user);
        return "redirect:login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpServletResponse response) {
        log.info("Запрос GET успешно обработан /login");
        User foundUser = userService.findByUsername(username);
        if (username.equals(foundUser.getUsername()) && BCrypt.checkpw(password, foundUser.getPassword())) {
            model.addAttribute("message", "Успешный вход!");
            sessionService.createSession(foundUser, response);
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Неверное имя пользователя или пароль.");
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req){
        log.info("Запрос GET успешно обработан /logout");
        sessionService.delete(req);
        return "redirect:/auth/login";
    }
}
