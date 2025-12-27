package com.pulsecare.backend.module.resource.bed.service;

import com.pulsecare.backend.common.exception.ResourceAlreadyExistsException;
import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.resource.bed.dto.BedReqDTO;
import com.pulsecare.backend.module.resource.bed.dto.BedResDTO;
import com.pulsecare.backend.module.resource.bed.mapper.BedMapper;
import com.pulsecare.backend.module.resource.bed.model.Bed;
import com.pulsecare.backend.module.resource.bed.repository.BedRepository;
import com.pulsecare.backend.module.resource.ward.model.Ward;
import com.pulsecare.backend.module.resource.ward.service.WardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    private final BedRepository repository;
    private final BedMapper mapper;
    private final WardService wardService;

    public BedServiceImpl(BedRepository repository, @Qualifier("bedMapperImpl") BedMapper mapper, WardService wardService) {
        this.repository = repository;
        this.mapper = mapper;
        this.wardService = wardService;
    }

    @Override
    public BedResDTO findById(Long id) {
        return mapper.toDTO(
                repository.findById(id)
                        .orElseThrow(() ->  new ResourceNotFoundException("Bed with id " + id + " not found")));
    }

    @Override
    public BedResDTO findByBedNoAndWardId(String bedNo, Integer wardId) {
        return mapper.toDTO(
                repository.findBYBedNoAndWard_Id(bedNo, wardId)
                        .orElseThrow(() ->  new ResourceNotFoundException("Bed with Bed No " + bedNo + " not found in ward " + wardId)));
    }

    @Override
    public List<BedResDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public Bed findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Bed with id " + id + " not found"));
    }

    @Override
    @Transactional
    public BedResDTO save(BedReqDTO data) {

        validateBedNoUniqueness(data.bedNo(), data.wardId(), null, "new");

        Ward ward = wardService.findById(data.wardId());

        Bed bed = mapper.toEntity(data);
        bed.setWard(ward);

        Bed saved = repository.save(bed);

        if (data.bedNo() != null) {
            saved.setBedNo(data.bedNo());
        } else {
            saved.setBedNo(ward.getName().substring(0, 2).toUpperCase()+ "-" + saved.getId());
        }

        return mapper.toDTO(repository.save(saved));
    }

    @Override
    @Transactional
    public List<BedResDTO> batchSave(Integer wardId, List<BedReqDTO> data) {
        Ward ward = wardService.findById(wardId);

        List<Bed> bedEntities = data.stream().map(bedDto -> {
            Bed bed = mapper.toEntity(bedDto);
            bed.setWard(ward);

            if (bedDto.bedNo() != null) {
                validateBedNoUniqueness(bedDto.bedNo(), wardId, null, "new");
                bed.setBedNo(bedDto.bedNo());
            }
            return bed;
        }).toList();

        List<Bed> savedBeds = repository.saveAll(bedEntities);

        for (Bed bed : savedBeds) {
            if (bed.getBedNo() == null) {
                bed.setBedNo(ward.getName().substring(0, 2).toUpperCase() + "-" + bed.getId());
            }
        }

        savedBeds = repository.saveAll(savedBeds);

        return savedBeds.stream()
                .map(mapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public BedResDTO update(Long id, BedReqDTO data) {
        validateBedNoUniqueness(data.bedNo(), data.wardId(), id, "existing");

        Bed existing = findEntityById(id);
        mapper.updateEntity(data, existing);
        existing.setWard(wardService.findById(data.wardId()));

        Bed updated = repository.save(existing);

        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Bed entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bed not found"));

        repository.delete(entity);
    }

    private void validateBedNoUniqueness(String bedNo, Integer wardId, Long bedId, String type) {
        if (bedNo != null) {
            if (type.equalsIgnoreCase("new")) {
                repository.findBYBedNoAndWard_Id(bedNo, wardId).ifPresent(i -> {
                    throw new ResourceAlreadyExistsException(
                            "Bed with Bed No " + bedNo + " already exists in this ward"
                    );
                });
            } else if (type.equalsIgnoreCase("existing")) {
                Bed byBedNo = repository.findBYBedNoAndWard_Id(bedNo, wardId).orElse(null);
                if (byBedNo != null && !byBedNo.getId().equals(bedId)) {
                    throw new ResourceAlreadyExistsException(
                            "Bed with Bed No " + bedNo + " already exists in this ward"
                    );
                }
            }
        }
    }


}
