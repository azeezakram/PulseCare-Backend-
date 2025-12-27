package com.pulsecare.backend.module.prescription.service;

import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.patient_admission.enums.PatientAdmissionStatus;
import com.pulsecare.backend.module.patient_admission.model.PatientAdmission;
import com.pulsecare.backend.module.patient_admission.service.PatientAdmissionService;
import com.pulsecare.backend.module.patient_queue.enums.QueueStatus;
import com.pulsecare.backend.module.patient_queue.model.PatientQueue;
import com.pulsecare.backend.module.patient_queue.service.PatientQueueService;
import com.pulsecare.backend.module.prescription.dto.*;
import com.pulsecare.backend.module.prescription.enums.PrescriptionStatus;
import com.pulsecare.backend.module.prescription.enums.PrescriptionType;
import com.pulsecare.backend.module.prescription.mapper.PrescriptionMapper;
import com.pulsecare.backend.module.prescription.model.Prescription;
import com.pulsecare.backend.module.prescription.model.PrescriptionItem;
import com.pulsecare.backend.module.prescription.repository.PrescriptionItemRepository;
import com.pulsecare.backend.module.prescription.repository.PrescriptionRepository;
import com.pulsecare.backend.module.user.model.Users;
import com.pulsecare.backend.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final PatientQueueService patientQueueService;
    private final PatientAdmissionService patientAdmissionService;
    private final UserService userService;
    private final PrescriptionMapper mapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PrescriptionItemRepository prescriptionItemRepository, PatientQueueService patientQueueService, PatientAdmissionService patientAdmissionService, UserService userService,
                                   @Qualifier("prescriptionMapperImpl") PrescriptionMapper mapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionItemRepository = prescriptionItemRepository;
        this.patientQueueService = patientQueueService;
        this.patientAdmissionService = patientAdmissionService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public PrescriptionSummaryResDTO findById(Long id) {
        return mapper.toSummaryDTO(
                prescriptionRepository.findById(id)
                        .orElseThrow(() ->  new ResourceNotFoundException("Patient admission with id " + id + " not found"))
        );
    }

    @Override
    public PrescriptionDetailResDTO findWithDetailById(Long id) {
        return mapper.toDetailDTO(
                prescriptionRepository.findById(id)
                        .orElseThrow(() ->  new ResourceNotFoundException("Patient admission with id " + id + " not found"))
        );
    }

    @Override
    public Prescription findEntityById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Patient admission with id " + id + " not found"));
    }

    @Override
    public List<PrescriptionSummaryResDTO> findAll() {
        return prescriptionRepository.findAll().stream()
                .map(mapper::toSummaryDTO)
                .toList();
    }

    @Override
    @Transactional
    public PrescriptionDetailResDTO save(PrescriptionReqDTO data) {

        PatientQueue queue = null;
        PatientAdmission admission = null;
        PrescriptionType type;

        // ---------- OPD / IPD Resolution ----------
        if (data.queueId() != null) {
            queue = patientQueueService.findEntityById(data.queueId());

            if (queue.getStatus() != QueueStatus.WAITING) {
                throw new IllegalStateException("Patient is not in WAITING queue status");
            }

            type = PrescriptionType.OPD;

        } else if (data.admissionId() != null) {
            admission = patientAdmissionService.findEntityById(data.admissionId());

            if (admission.getStatus() != PatientAdmissionStatus.ACTIVE) {
                throw new IllegalStateException("Patient is not currently admitted");
            }

            type = PrescriptionType.IPD;

        } else {
            throw new IllegalArgumentException("Either queueId or admissionId must be provided");
        }

        Users doctor = userService.findById(data.doctorId());

        boolean hasItems = data.items() != null && !data.items().isEmpty();
        PrescriptionStatus status = hasItems
                ? PrescriptionStatus.FINALIZED
                : PrescriptionStatus.DRAFT;

        // ---------- Prescription ----------
        Prescription prescription = Prescription.builder()
                .doctor(doctor)
                .patientQueue(queue)
                .admission(admission)
                .type(type)
                .notes(data.notes())
                .status(status)
                .build();

        Prescription savedPrescription = prescriptionRepository.save(prescription);

        // ---------- Items ----------
        if (hasItems) {
            List<PrescriptionItem> items = data.items().stream()
                    .map(itemDto -> PrescriptionItem.builder()
                            .prescription(savedPrescription)
                            .medicineName(itemDto.medicineName())
                            .dosage(itemDto.dosage())
                            .frequency(itemDto.frequency())
                            .durationDays(itemDto.durationDays())
                            .instructions(itemDto.instructions())
                            .build()
                    )
                    .toList();

            prescriptionItemRepository.saveAll(items);
        }

        List<PrescriptionItemResDTO> resItems =
                hasItems
                        ? prescriptionItemRepository.findAllByPrescriptionId(savedPrescription.getId())
                        .stream()
                        .map(mapper::toDTO)
                        .toList()
                        : List.of();

        return new PrescriptionDetailResDTO(
                savedPrescription.getId(),
                doctor.getFirstName() + doctor.getLastName(),
                admission != null ? admission.getId() : null,
                queue != null ? queue.getId() : null,
                savedPrescription.getType(),
                savedPrescription.getNotes(),
                savedPrescription.getStatus().name(),
                resItems,
                savedPrescription.getCreatedAt(),
                savedPrescription.getUpdatedAt()
        );
    }

    @Override
    public PrescriptionDetailResDTO update(Long aLong, PrescriptionReqDTO data) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Prescription entity = findEntityById(id);
        prescriptionRepository.delete(entity);
    }

}
