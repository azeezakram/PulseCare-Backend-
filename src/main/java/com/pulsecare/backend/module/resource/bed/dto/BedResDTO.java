package com.pulsecare.backend.module.resource.bed.dto;

import java.time.LocalDateTime;

public record BedResDTO(
        Long id,
        String bedNo,
        Boolean isTaken,
        Integer wardId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
