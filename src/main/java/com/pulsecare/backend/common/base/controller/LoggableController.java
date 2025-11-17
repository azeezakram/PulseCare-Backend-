package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface LoggableController<T, R> {
    ResponseEntity<R> login(T data, BindingResult result);
}
