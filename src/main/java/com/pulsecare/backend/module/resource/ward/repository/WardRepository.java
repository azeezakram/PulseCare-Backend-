package com.pulsecare.backend.module.resource.ward.repository;

import com.pulsecare.backend.module.resource.ward.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    Optional<Ward> findByName(String name);
}
