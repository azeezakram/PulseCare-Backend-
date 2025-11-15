package com.pulsecare.backend.common.base.service;

import org.springframework.http.ResponseEntity;

public interface DeletableService<T, R> {
    R delete(T id);
}
