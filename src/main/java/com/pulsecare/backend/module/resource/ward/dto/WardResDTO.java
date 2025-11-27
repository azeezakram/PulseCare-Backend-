package com.pulsecare.backend.module.resource.ward.dto;

import java.time.LocalDateTime;

public record WardResDTO(
        Integer id,
        String name,
        Integer departmentId,
        Integer bedCount,
        Integer occupiedBeds,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
