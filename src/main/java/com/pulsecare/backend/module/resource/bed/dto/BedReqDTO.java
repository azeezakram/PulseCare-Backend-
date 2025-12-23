package com.pulsecare.backend.module.resource.bed.dto;

public record BedReqDTO(
        String bedNo,
        Boolean isTaken,
        Integer wardId
) {}

