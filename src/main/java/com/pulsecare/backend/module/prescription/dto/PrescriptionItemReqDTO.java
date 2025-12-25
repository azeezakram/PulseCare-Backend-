package com.pulsecare.backend.module.prescription.dto;

public record PrescriptionItemReqDTO(
        Long prescriptionId,
        String medicineName,
        String dosage,
        String frequency,
        Integer durationDays,
        String instructions
) {}

