package com.pulsecare.backend.module.doctordetail.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record DoctorDetailReqDto(
        @NotEmpty(message = "License No is required")
        String licenseNo,
        @NotEmpty(message = "User ID is required")
        String userId,
        @NotEmpty(message = "Specialization IDs are required")
        Set<Integer> specializationIds
) {
}
