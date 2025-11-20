package com.pulsecare.backend.module.resource.department.repository;

import com.pulsecare.backend.module.resource.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
