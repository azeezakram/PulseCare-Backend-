package com.pulsecare.backend.module.user.dto;

public record LoginRequestDTO(
        String username,
        String password
) {
}
