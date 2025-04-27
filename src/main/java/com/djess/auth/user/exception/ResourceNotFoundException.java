package com.djess.auth.user.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s: '%s'",
                Objects.requireNonNull(resourceName, "resourceName must not be null"),
                Objects.requireNonNull(fieldName, "fieldName must not be null"),
                Objects.requireNonNull(fieldValue, "fieldValue must not be null")
        ), HttpStatus.NOT_FOUND);
    }
}
