package com.pulsecare.backend.module.prescription.dto;

public record PrescriptionItemResDTO(
        Long id,
        Long prescriptionId,
        String medicineName,
        String dosage,
        String frequency,
        Integer durationDays,
        String instructions
) {}

