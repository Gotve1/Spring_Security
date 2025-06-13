package com.example.security.Configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/users/add/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST")
                .allowedHeaders("Content-Type", "Authorization");
    }
}