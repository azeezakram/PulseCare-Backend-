package com.pulsecare.backend.module.patient.dto;

import java.time.LocalDateTime;

public record PatientResDTO(
        Long id,
        String fullName,
        Integer age,
        String bloodGroup,
        String nic,
        String gender,
        String phone,
        LocalDateTime createdAt
) {}
