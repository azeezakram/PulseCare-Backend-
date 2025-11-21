package com.pulsecare.backend.module.role.dto;

import java.time.LocalDateTime;

public record RoleResDto(
        Integer id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
