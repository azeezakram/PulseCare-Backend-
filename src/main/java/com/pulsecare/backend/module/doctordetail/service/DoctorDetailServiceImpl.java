package com.pulsecare.backend.module.doctordetail.service;

import com.pulsecare.backend.common.exception.ResourceAlreadyExistsException;
import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import com.pulsecare.backend.module.doctordetail.repository.DoctorDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    private final DoctorDetailRepository repository;

    public DoctorDetailServiceImpl(DoctorDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public DoctorDetail findById(Long id) {
        DoctorDetail data = repository.findById(id).orElse(null);
        if (data == null) {
            throw new ResourceNotFoundException("Doctor detail with id " + id + " not found");
        }
        return data;
    }

    @Override
    public List<DoctorDetail> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public DoctorDetail create(DoctorDetail data) {
        repository.findByUserIdAndLicenseNo(data.getUser().getId(), data.getLicenseNo())
                .ifPresent(s -> {
                    throw new ResourceAlreadyExistsException(
                            "Doctor detail with this User ID or License No already exists");
                });

        return repository.save(data);
    }

    @Override
    public DoctorDetail update(Long id, DoctorDetail data) {
        DoctorDetail existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail with id " + id + " not found"));

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        DoctorDetail entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor detail not found"));

        repository.delete(entity);
    }

}
