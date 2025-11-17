package com.pulsecare.backend.common.base.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FindableService<T, R> {
    R findById(T id);
    List<R> findAll();
}
