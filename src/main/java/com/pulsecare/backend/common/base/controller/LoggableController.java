package com.pulsecare.backend.common.base.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface LoggableController<T, R> {
    ResponseEntity<R> login(@Valid T data);
}
