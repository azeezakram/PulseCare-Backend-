package com.pulsecare.backend.common.base.service;

import org.springframework.http.ResponseEntity;

public interface FindableService<T, R> {
    R findById(T id);
    R findAll();
}
