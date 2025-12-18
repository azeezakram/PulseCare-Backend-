package com.pulsecare.backend.module.triage.service;

import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.SavableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.triage.dto.TriageReqDTO;
import com.pulsecare.backend.module.triage.dto.TriageResDTO;
import com.pulsecare.backend.module.triage.model.Triage;

public interface TriageService extends
        FindableService<Long, TriageResDTO>,
        SavableService<TriageReqDTO, TriageResDTO>,
        UpdatableService<TriageReqDTO, TriageResDTO, Long>,
        DeletableService<Long> {
    Triage findEntityById(Long id);
    TriageResDTO predict(TriageReqDTO dto);
}
