package com.pulsecare.backend.common.base.controller;

import org.springframework.http.ResponseEntity;

public interface DeletableController<R, ID> {
    ResponseEntity<R> delete(ID id);
}
