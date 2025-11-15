package com.pulsecare.backend.module.user.admin.controller;

import com.pulsecare.backend.common.template.response.ResponseBody;
import com.pulsecare.backend.module.user.admin.dto.AdminRequestDTO;
import com.pulsecare.backend.module.user.admin.dto.AdminResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerImpl implements AdminController {

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<AdminResponseDTO>> findById(@PathVariable("id") String id) {
        return null;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<ResponseBody<AdminResponseDTO>> findAll() {
        return null;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<ResponseBody<AdminResponseDTO>> create(AdminRequestDTO data) {
        return null;
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<ResponseBody<AdminResponseDTO>> update(AdminRequestDTO data) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody<Byte>> delete(@PathVariable("id") String id) {
        return null;
    }

}
