package com.pulsecare.backend.module.resource.ward.dto;

public record WardReqDTO(
        String name,
        Integer departmentId,
        Integer bedCount,
        Integer occupiedBeds
) {

}
