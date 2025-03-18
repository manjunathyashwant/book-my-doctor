package com.tp.bookmydoctor.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("System"); // Replace with actual user from Security Context
    }
}
