package com.pulsecare.backend.module.patient.dto;

public record PatientReqDTO(
        String fullName,
        Integer age,
        String bloodGroup,
        String nic,
        String gender,
        String phone
) {}

