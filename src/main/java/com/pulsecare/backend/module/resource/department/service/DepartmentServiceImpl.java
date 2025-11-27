package com.pulsecare.backend.module.resource.department.service;

import com.pulsecare.backend.common.exception.ResourceAlreadyExistsException;
import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.resource.department.dto.DeptRequestDTO;
import com.pulsecare.backend.module.resource.department.dto.DeptResponseDTO;
import com.pulsecare.backend.module.resource.department.mapper.DepartmentMapper;
import com.pulsecare.backend.module.resource.department.model.Department;
import com.pulsecare.backend.module.resource.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " not found"));
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department save(Department data) {
        if (repository.findByName(data.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Department with name " + data.getName() + " already exists");
        }

        return repository.save(data);
    }

    @Override
    public void delete(Integer id) {
        Department existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));

        repository.delete(existing);
    }
}
