package ru.shaxowskiy.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(UnauthorizedException.class)
    public String handleException(){
        return "redirect:/auth/login";
    }
}
