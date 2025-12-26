package com.pulsecare.backend.module.prescription.mapper;

import com.pulsecare.backend.module.prescription.dto.*;
import com.pulsecare.backend.module.prescription.model.Prescription;
import com.pulsecare.backend.module.prescription.model.PrescriptionItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    // Prescription mappings

    @Mapping(source = "admission.id", target = "admissionId")
    @Mapping(source = "patientQueue.id", target = "queueId")
    @Mapping(target = "doctorName", expression = "java(entity.getDoctor().getFirstName() + \" \" + entity.getDoctor().getLastName())")
    PrescriptionSummaryResDTO toSummaryDTO(Prescription entity);

    @Mapping(source = "admission.id", target = "admissionId")
    @Mapping(source = "patientQueue.id", target = "queueId")
    @Mapping(target = "doctorName", expression = "java(entity.getDoctor().getFirstName() + \" \" + entity.getDoctor().getLastName())")
    @Mapping(target = "items", ignore = true)
    PrescriptionDetailResDTO toDetailDTO(Prescription entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patientQueue", ignore = true)
    @Mapping(target = "admission", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "items", ignore = true)
    Prescription toEntity(PrescriptionReqDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patientQueue", ignore = true)
    @Mapping(target = "admission", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "items", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PrescriptionReqDTO dto, @MappingTarget Prescription entity);


    // PrescriptionItem mappings

    @Mapping(source = "prescription.id", target = "prescriptionId")
    PrescriptionItemResDTO toDTO(PrescriptionItem entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    PrescriptionItem toEntity(PrescriptionItemReqDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PrescriptionItemReqDTO dto, @MappingTarget PrescriptionItem entity);

    // Prescription item list mappings

    List<PrescriptionItemResDTO> toItemDTOList(List<PrescriptionItem> items);

    List<PrescriptionItem> toItemEntityList(List<PrescriptionItemReqDTO> items);
}
