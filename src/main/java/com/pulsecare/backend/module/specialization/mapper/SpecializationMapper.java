package com.pulsecare.backend.module.specialization.mapper;

import com.pulsecare.backend.module.specialization.dto.SpecializationReqDTO;
import com.pulsecare.backend.module.specialization.dto.SpecializationResDTO;
import com.pulsecare.backend.module.specialization.model.Specialization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    Specialization toEntity(SpecializationReqDTO dto);
    SpecializationResDTO toDTO(Specialization entity);
}
