package com.pulsecare.backend.module.resource.department.repository;

import com.pulsecare.backend.module.resource.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
}
