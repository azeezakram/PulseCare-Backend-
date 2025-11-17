package com.pulsecare.backend.common.base.service;

public interface LoggableService<T, R> {
    R login(T data);
}
