package com.pulsecare.backend.module.patient_queue.service;

import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.SavableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.patient_queue.dto.PatientQueueReqDTO;
import com.pulsecare.backend.module.patient_queue.dto.PatientQueueResDTO;

public interface PatientQueueService extends
        FindableService<Long, PatientQueueResDTO>,
        SavableService<PatientQueueReqDTO, PatientQueueResDTO>,
        UpdatableService<PatientQueueReqDTO, PatientQueueResDTO, Long>,
        DeletableService<Long> {
}
