package com.pulsecare.backend.module.prescription.repository;

import com.pulsecare.backend.module.prescription.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
