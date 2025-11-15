package com.pulsecare.backend.module.user.admin.repository;

import com.pulsecare.backend.module.user.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
