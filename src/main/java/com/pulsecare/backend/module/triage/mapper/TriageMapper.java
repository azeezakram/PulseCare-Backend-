package com.pulsecare.backend.module.triage.mapper;

import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import com.pulsecare.backend.module.triage.dto.TriagePredictionReqDTO;
import com.pulsecare.backend.module.triage.dto.TriagePredictionResDTO;
import com.pulsecare.backend.module.triage.dto.TriageReqDTO;
import com.pulsecare.backend.module.triage.dto.TriageResDTO;
import com.pulsecare.backend.module.triage.model.Triage;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TriageMapper {
    Triage toEntity(TriageReqDTO dto);
    TriageResDTO toDTO(Triage entity);

    Triage toPredEntity(TriagePredictionResDTO dto);
    TriagePredictionReqDTO toPredDTO(Triage entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(TriageReqDTO dto, @MappingTarget Triage entity);
}
