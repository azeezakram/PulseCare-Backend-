package com.pulsecare.backend.module.resource.bed.mapper;

import com.pulsecare.backend.module.resource.bed.dto.BedReqDTO;
import com.pulsecare.backend.module.resource.bed.dto.BedResDTO;
import com.pulsecare.backend.module.resource.bed.model.Bed;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BedMapper {

    @Mapping(source = "ward.id", target = "wardId")
    BedResDTO toDTO(Bed entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ward", ignore = true)
    @Mapping(target = "isTaken", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Bed toEntity(BedReqDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ward", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(
            BedReqDTO dto,
            @MappingTarget Bed entity
    );

}

