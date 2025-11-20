package com.pulsecare.backend.common.base.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UpdatableController<T, R, ID> {
    ResponseEntity<R> update(@Valid ID id, T data, BindingResult result);
}
