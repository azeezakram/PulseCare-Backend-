package com.pulsecare.backend.module.resource.ward.maper;

import com.pulsecare.backend.module.resource.ward.dto.WardReqDTO;
import com.pulsecare.backend.module.resource.ward.dto.WardResDTO;
import com.pulsecare.backend.module.resource.ward.model.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WardMapper {
    @Mapping(target = "department", ignore = true)
    Ward toEntity(WardReqDTO dto);
    WardResDTO toDTO(Ward obj);
}
