package com.pulsecare.backend.module.doctordetail.mapper;

import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailResDto;
import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorDetailMapper {
    DoctorDetail toEntity(DoctorDetailReqDto dto);

    @Mapping(source = "user.id", target = "userId")
    DoctorDetailResDto toDTO(DoctorDetail entity);
}
