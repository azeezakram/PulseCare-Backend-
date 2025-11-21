package com.pulsecare.backend.module.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleReqDto(
        @NotBlank(message = "Name is required")
        @NotNull(message = "Name cannot be null")
        String name
) {
}
