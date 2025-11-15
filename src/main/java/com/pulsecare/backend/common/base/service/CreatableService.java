package com.pulsecare.backend.common.base.service;

public interface CreatableService<T, R> {
    R create(T data);
}
