package com.pulsecare.backend.module.prescription.dto;

import java.time.LocalDateTime;

public record PrescriptionSummaryResDTO(
        Long id,
        Long admissionId,
        String doctorName,
        String status,
        LocalDateTime createdAt
) {
}
