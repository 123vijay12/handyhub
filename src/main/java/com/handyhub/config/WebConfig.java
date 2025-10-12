package com.handyhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all paths
                        .allowedOrigins("*") // Allow all origins, or specify your frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH") // Allow methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(false); // Set to true if using cookies or auth headers
            }
        };
    }
}
