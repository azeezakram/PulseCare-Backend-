package com.pulsecare.backend.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotBlank(message = "Username is required")
        @NotNull(message = "Username cannot be null")
        String username,
        @NotBlank(message = "Password is required")
        @NotNull(message = "Password cannot be null")
        String password
) {
}
