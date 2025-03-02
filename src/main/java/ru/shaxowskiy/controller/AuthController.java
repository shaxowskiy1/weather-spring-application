package ru.shaxowskiy.controller;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.models.dto.UserDTO;
import ru.shaxowskiy.services.SessionService;
import ru.shaxowskiy.services.UserService;
import ru.shaxowskiy.util.PasswordIsNotConfirmValidator;
import ru.shaxowskiy.util.UsernameAlreadyTakenValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PasswordIsNotConfirmValidator passwordIsNotConfirmValidator;
    private final UsernameAlreadyTakenValidator usernameAlreadyTakenValidator;
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public AuthController(PasswordIsNotConfirmValidator passwordIsNotConfirmValidator, UsernameAlreadyTakenValidator usernameAlreadyTakenValidator, SessionService sessionService, UserService userService) {
        this.passwordIsNotConfirmValidator = passwordIsNotConfirmValidator;
        this.usernameAlreadyTakenValidator = usernameAlreadyTakenValidator;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserDTO());
        log.info("GET-method register with form");
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") @Valid UserDTO userDTO,
                               BindingResult bindingResult){
        passwordIsNotConfirmValidator.validate(userDTO, bindingResult);
        usernameAlreadyTakenValidator.validate(userDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.registerUser(userDTO);
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
        sessionService.delete(req);
        return "redirect:/auth/login";
    }
}
