package com.pulsecare.backend.common.template.response;

import java.time.LocalDateTime;

public record ErrorResponseBody(int status, String message, LocalDateTime timestamp) {
    public ErrorResponseBody(int status, String message) {
        this(status, message, LocalDateTime.now());
    }
}

