package com.pulsecare.backend.module.resource.department.dto;

import jakarta.validation.constraints.NotEmpty;

public record DeptRequestDTO(
        @NotEmpty(message = "Name is required")
        String name) {
}
