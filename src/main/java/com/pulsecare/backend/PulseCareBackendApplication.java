package com.pulsecare.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "PulseCare Backend API Documentation", version = "1.0", description = "API documentation for PulseCare Backend"))
public class PulseCareBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulseCareBackendApplication.class, args);
    }

}
