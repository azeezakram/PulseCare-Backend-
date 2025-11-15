package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface DeletableController<T, R> {
    ResponseEntity<R> delete(T id);
}
