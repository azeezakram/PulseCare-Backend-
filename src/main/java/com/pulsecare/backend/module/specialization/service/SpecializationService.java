package com.pulsecare.backend.module.specialization.service;

import com.pulsecare.backend.common.base.service.CreatableService;
import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.specialization.dto.SpecializationReqDTO;
import com.pulsecare.backend.module.specialization.dto.SpecializationResDTO;

public interface SpecializationService extends
        FindableService<Integer, SpecializationResDTO>,
        CreatableService<SpecializationReqDTO, SpecializationResDTO>,
        UpdatableService<SpecializationReqDTO, SpecializationResDTO, Integer>,
        DeletableService<Integer> {
}
