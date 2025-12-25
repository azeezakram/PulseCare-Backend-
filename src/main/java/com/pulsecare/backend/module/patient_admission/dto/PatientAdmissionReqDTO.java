package com.pulsecare.backend.module.patient_admission.dto;

import com.pulsecare.backend.module.patient_admission.enums.PatientAdmissionStatus;

public record PatientAdmissionReqDTO(

        Long patientId,
        Long queueId,
        Long bedId,
        PatientAdmissionStatus status,
        String dischargeNotes

) {}
