package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface UpdatableController<T, R> {
    ResponseEntity<R> update(T data);
}
