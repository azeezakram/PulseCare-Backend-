package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface FindableController<T, R> {
    ResponseEntity<R> findById(T id);
    ResponseEntity<R> findAll();
}
