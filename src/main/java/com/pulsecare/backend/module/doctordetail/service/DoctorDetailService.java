package com.pulsecare.backend.module.doctordetail.service;

import com.pulsecare.backend.common.base.service.CreatableService;
import com.pulsecare.backend.common.base.service.DeletableService;
import com.pulsecare.backend.common.base.service.FindableService;
import com.pulsecare.backend.common.base.service.UpdatableService;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailResDto;

public interface DoctorDetailService extends
        FindableService<Long, DoctorDetailResDto>,
        CreatableService<DoctorDetailReqDto, DoctorDetailResDto>,
        UpdatableService<DoctorDetailReqDto, DoctorDetailResDto, Long>,
        DeletableService<Long> {
}
