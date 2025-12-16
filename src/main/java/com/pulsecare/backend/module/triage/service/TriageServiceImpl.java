package com.pulsecare.backend.module.triage.service;

import com.pulsecare.backend.common.exception.ResourceNotFoundException;
import com.pulsecare.backend.module.triage.dto.TriageReqDTO;
import com.pulsecare.backend.module.triage.dto.TriageResDTO;
import com.pulsecare.backend.module.triage.mapper.TriageMapper;
import com.pulsecare.backend.module.triage.model.Triage;
import com.pulsecare.backend.module.triage.repository.TriageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriageServiceImpl implements TriageService {

    private final TriageRepository repository;
    private final TriageMapper mapper;

    public TriageServiceImpl(TriageRepository repository, TriageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TriageResDTO findById(Long id) {
        Triage data = repository.findById(id).orElse(null);
        if (data == null) {
            throw new ResourceNotFoundException("Triage with id " + id + " not found");
        }
        return mapper.toDTO(data);
    }

    @Override
    public List<TriageResDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TriageResDTO save(TriageReqDTO data) {
        Triage entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TriageResDTO update(Long id, TriageReqDTO data) {
        Triage existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Triage with id " + id + " not found"));

        mapper.updateEntity(data, existing);

        return mapper.toDTO(repository.save(existing));
    }

    @Override
    public Triage predict(Triage dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}
