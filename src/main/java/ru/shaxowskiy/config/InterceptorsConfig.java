package ru.shaxowskiy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.shaxowskiy.controller.interceptors.AuthInterceptor;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public InterceptorsConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/welcome/**", "/welcome");
    }
}
