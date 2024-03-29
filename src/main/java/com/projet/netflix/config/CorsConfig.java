package com.projet.netflix.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedMethods("*")
                .allowedOrigins("*"); // Remplacez par l'URL de votre application Angular
    }
}

