package com.pulsecare.backend.common.base.service;

import org.springframework.http.ResponseEntity;

public interface UpdatableService<T, R> {
    R update(T data);
}
