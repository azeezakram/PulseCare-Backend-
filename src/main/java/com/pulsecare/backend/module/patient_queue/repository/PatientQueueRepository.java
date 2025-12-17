package com.pulsecare.backend.module.patient_queue.repository;

import com.pulsecare.backend.module.patient_queue.model.PatientQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientQueueRepository extends JpaRepository<PatientQueue, Long> {
}
