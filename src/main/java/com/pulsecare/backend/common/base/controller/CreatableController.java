package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface CreatableController<T, R> {
    ResponseEntity<R> create(T data);
}
