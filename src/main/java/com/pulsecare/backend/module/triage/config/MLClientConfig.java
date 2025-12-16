package com.pulsecare.backend.module.triage.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MLClientConfig {

    @Value("${ml.model.api.key}")
    private String apiKey;

    @Value("${ml.model.api.key.header}")
    private String apiKeyHeader;

    @Value("${ml.model.api.url}")
    private String mlApiUrl;
}

