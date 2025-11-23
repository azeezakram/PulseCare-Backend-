package com.pulsecare.backend.module.specialization.service;

import com.pulsecare.backend.common.base.service.CreatableService;
import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.specialization.model.Specialization;

import java.util.Set;

public interface SpecializationService extends
        FindableService<Integer, Specialization>,
        CreatableService<Specialization, Specialization>,
        UpdatableService<Specialization, Specialization, Integer>,
        DeletableService<Integer> {
    Set<Specialization> findAllById(Set<Integer> ids);
}
