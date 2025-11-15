package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface FindableController<R, ID> {
    ResponseEntity<R> findById(ID id);
    ResponseEntity<R> findAll();
}
