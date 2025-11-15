package com.pulsecare.backend.module.user.admin.service;

import com.pulsecare.backend.module.user.admin.dto.AdminRequestDTO;
import com.pulsecare.backend.module.user.admin.dto.AdminResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public AdminResponseDTO create(AdminRequestDTO data) {
        return null;
    }

    @Override
    public String delete(Byte id) {
        return "";
    }

    @Override
    public AdminResponseDTO findById(String id) {
        return null;
    }

    @Override
    public AdminResponseDTO findAll() {
        return null;
    }

    @Override
    public AdminResponseDTO update(AdminRequestDTO data) {
        return null;
    }
}
