package com.java.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig /*implements WebMvcConfigurer*/ {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // pozwól na wszystkie ścieżki
                        .allowedOriginPatterns("*")
//                        .allowedOrigins("http://localhost:4200")  // zezwól na pochodzenie z Angulara (możesz dodać inne, jeśli potrzebujesz)
                        .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE")  // metody HTTP, które chcesz zezwolić
                        .allowedHeaders("*")  // zezwól na wszystkie nagłówki
                        .allowCredentials(true);  // jeśli potrzebujesz obsługi ciasteczek
            }
        };
    }

}
