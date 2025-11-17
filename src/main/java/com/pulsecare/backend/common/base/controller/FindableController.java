package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FindableController<R1, R2, ID> {
    ResponseEntity<R1> findById(ID id);
    ResponseEntity<R2> findAll();
}
