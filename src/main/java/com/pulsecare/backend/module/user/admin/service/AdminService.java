package com.pulsecare.backend.module.user.admin.service;

import com.pulsecare.backend.common.base.service.CreatableService;
import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.user.admin.dto.AdminRequestDTO;
import com.pulsecare.backend.module.user.admin.dto.AdminResponseDTO;

public interface AdminService extends
        FindableService<String, AdminResponseDTO>,
        CreatableService<AdminRequestDTO, AdminResponseDTO>,
        UpdatableService<AdminRequestDTO, AdminResponseDTO>,
        DeletableService<Byte, String> {
}
