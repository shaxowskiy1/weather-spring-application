package ru.shaxowskiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest req, HttpServletResponse res, Model model){
//        HttpSession httpSession = req.getSession();
//
//        Integer count = (Integer) httpSession.getAttribute("count");
//
//        if(count == null) {
//            httpSession.setAttribute("count", 1);
//            count = 1;
//        } else {
//            httpSession.setAttribute("count", count + 1);
//        }
//        model.addAttribute("count", count);
        return "welcome";
    }
}
