package com.pulsecare.backend.module.resource.bed.repository;

import com.pulsecare.backend.module.resource.bed.model.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
    Optional<Bed> findByBedNoAndWard_Id(String bedNo, Integer wardId);
}
