package com.pulsecare.backend.module.triage.dto;

public record TriagePredictionResDTO(
        Integer triageLevel, // 0 = Critical, 1 = Non-Critical
        Double confidence,
        String severity, // "CRITICAL" or "NON_CRITICAL"

        // All input features echoed back
        Integer sex, // 0=Female, 1=Male
        Integer arrivalMode,
        Integer injury,
        Integer mental,
        Integer pain,
        Integer age,
        Integer sbp,
        Integer dbp,
        Integer hr,
        Integer rr,
        Double bt,

        // Calculated features
        Double shockIndex,
        Double pulsePressure,
        Double ppRatio,
        Double hrBtInteraction,
        Double rrHrRatio,
        Boolean isFever,
        Boolean isTachy,
        Boolean isLowSbp,
        Boolean isLowDbp,
        Boolean isTachypnea
) {}