package com.pulsecare.backend.module.patient_queue.dto;

public record PatientQueueReqDTO(
        String patientName,
        Integer age,

        Long triageId,

        // optional manual priority (NORMAL default)
        String priority  // CRITICAL | NON_CRITICAL | NORMAL
) {}

