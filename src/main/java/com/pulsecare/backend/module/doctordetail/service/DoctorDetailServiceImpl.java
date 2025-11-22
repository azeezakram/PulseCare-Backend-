package com.pulsecare.backend.module.doctordetail.service;

import com.pulsecare.backend.common.exception.ResourceAlreadyExistsException;
import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailResDto;
import com.pulsecare.backend.module.doctordetail.mapper.DoctorDetailMapper;
import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import com.pulsecare.backend.module.doctordetail.repository.DoctorDetailRepository;
import com.pulsecare.backend.module.role.model.Role;
import com.pulsecare.backend.module.specialization.model.Specialization;
import com.pulsecare.backend.module.specialization.repository.SpecializationRepository;
import com.pulsecare.backend.module.specialization.service.SpecializationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    private final DoctorDetailRepository repository;
    private final DoctorDetailMapper mapper;
    private final SpecializationRepository specializationRepository;

    public DoctorDetailServiceImpl(DoctorDetailRepository repository, @Qualifier("doctorDetailMapperImpl") DoctorDetailMapper mapper, SpecializationRepository specializationRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.specializationRepository = specializationRepository;
    }

    @Override
    public DoctorDetailResDto findById(Long id) {
        DoctorDetail data = repository.findById(id).orElse(null);

        return Optional.ofNullable(data)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail not found"));
    }

    @Override
    public List<DoctorDetailResDto> findAll() {
        List<DoctorDetail> data = repository.findAll();
        return data.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public DoctorDetailResDto create(DoctorDetailReqDto data) {
        repository.findByUserIdAndLicenseNo(UUID.fromString(data.userId()), data.licenseNo())
                .ifPresent(s -> {
                    throw new ResourceAlreadyExistsException("Doctor detail with this User ID or License No already exists");
                });
        DoctorDetail entity = repository.save(mapper.toEntity(data));
        return mapper.toDTO(entity);
    }

    @Override
    public DoctorDetailResDto update(Long id, DoctorDetailReqDto data) {
        DoctorDetail existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail with id " + id + " not found"));

        existing.setLicenseNo(data.licenseNo());
        Set<Specialization> specializationIds = new HashSet<>(specializationRepository.findAllById(data.specializationIds()));
        existing.setSpecializations(specializationIds);

        DoctorDetail updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        DoctorDetail entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail not found"));

        repository.delete(entity);
    }



}
