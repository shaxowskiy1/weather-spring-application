package ru.shaxowskiy.controller.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.shaxowskiy.exception.UnauthorizedException;
import ru.shaxowskiy.services.SessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    @Autowired
    public AuthInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!sessionService.isValid(request.getCookies())){
            throw new UnauthorizedException("User not authorize");
        }
        return true;
    }
}
