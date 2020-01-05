package com.rename.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String stringBean() {
        return "This is a string bean!";
    }
}
