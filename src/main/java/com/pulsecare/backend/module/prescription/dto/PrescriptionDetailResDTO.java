package com.pulsecare.backend.module.prescription.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrescriptionDetailResDTO(
        Long id,
        Long admissionId,
        String doctorName,
        String status,
        List<PrescriptionItemResDTO> items,
        LocalDateTime createdAt
) {}

