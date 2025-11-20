package com.pulsecare.backend.common.base.service;

public interface DeletableService<T> {
    void delete(T id);
}
