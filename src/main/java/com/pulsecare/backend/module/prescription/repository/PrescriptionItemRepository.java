package com.pulsecare.backend.module.prescription.repository;

import com.pulsecare.backend.module.prescription.model.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, Long> {
}
