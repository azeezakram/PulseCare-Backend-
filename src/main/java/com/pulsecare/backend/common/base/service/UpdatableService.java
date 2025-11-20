package com.pulsecare.backend.common.base.service;

public interface UpdatableService<T, R, ID> {
    R update(ID id, T data);
}
