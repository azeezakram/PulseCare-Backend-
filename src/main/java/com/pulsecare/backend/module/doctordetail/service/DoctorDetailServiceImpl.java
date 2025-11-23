package com.pulsecare.backend.module.doctordetail.service;

import com.pulsecare.backend.common.exception.ResourceAlreadyExistsException;
import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailResDto;
import com.pulsecare.backend.module.doctordetail.mapper.DoctorDetailMapper;
import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import com.pulsecare.backend.module.doctordetail.repository.DoctorDetailRepository;
import com.pulsecare.backend.module.specialization.model.Specialization;
import com.pulsecare.backend.module.specialization.repository.SpecializationRepository;
import com.pulsecare.backend.module.user.model.Users;
import com.pulsecare.backend.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    private final DoctorDetailRepository repository;
    private final DoctorDetailMapper mapper;
    private final SpecializationRepository specializationRepository;
    private final UserRepository userRepository;

    public DoctorDetailServiceImpl(DoctorDetailRepository repository, @Qualifier("doctorDetailMapperImpl") DoctorDetailMapper mapper, SpecializationRepository specializationRepository, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.specializationRepository = specializationRepository;
        this.userRepository = userRepository;
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
    @Transactional
    public DoctorDetailResDto create(DoctorDetailReqDto data) {
        // Check if doctor detail already exists
        repository.findByUserIdAndLicenseNo(UUID.fromString(data.userId()), data.licenseNo())
                .ifPresent(s -> {
                    throw new ResourceAlreadyExistsException(
                            "Doctor detail with this User ID or License No already exists");
                });

//         Fetch the User entity
        Users user = userRepository.findById(UUID.fromString(data.userId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + data.userId()));

        // Fetch the Specialization entities
        Set<Specialization> specializations = new HashSet<>();
        if (data.specializationIds() != null && !data.specializationIds().isEmpty()) {
            specializations = new HashSet<>(
                    specializationRepository.findAllById(data.specializationIds()));

            // Validate that all specializations were found
            if (specializations.size() != data.specializationIds().size()) {
                throw new ResourceNotFoundException(
                        "One or more specializations not found");
            }
        }

        // Create DoctorDetail entity
        DoctorDetail doctorDetail = new DoctorDetail();
        doctorDetail.setLicenseNo(data.licenseNo());
        doctorDetail.setUser(user);
        doctorDetail.setSpecializations(specializations);

        // Save and return
        DoctorDetail savedEntity = repository.save(doctorDetail);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public DoctorDetailResDto update(Long id, DoctorDetailReqDto data) {
        DoctorDetail existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail with id " + id + " not found"));

        Users user = userRepository.findById(UUID.fromString(data.userId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + data.userId()));

        existing.setUser(user);
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
