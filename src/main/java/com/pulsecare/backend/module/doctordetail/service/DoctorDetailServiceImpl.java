package com.pulsecare.backend.module.doctordetail.service;

import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailReqDto;
import com.pulsecare.backend.module.doctordetail.dto.DoctorDetailResDto;
import com.pulsecare.backend.module.doctordetail.repository.DoctorDetailRepository;
import com.pulsecare.backend.module.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    private final DoctorDetailRepository repository;
    private final RoleMapper mapper;

    public DoctorDetailServiceImpl(DoctorDetailRepository repository, @Qualifier("roleMapperImpl") RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public DoctorDetailResDto create(DoctorDetailReqDto data) {
        return null;
    }

    @Override
    public void delete(Long id) {
        //TODO Auto-generated method stub
    }

    @Override
    public DoctorDetailResDto findById(Long id) {
        return null;
    }

    @Override
    public List<DoctorDetailResDto> findAll() {
        return List.of();
    }

    @Override
    public DoctorDetailResDto update(Long aLong, DoctorDetailReqDto data) {
        return null;
    }
}
