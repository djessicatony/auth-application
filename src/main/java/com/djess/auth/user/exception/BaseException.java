package com.djess.auth.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
public abstract class BaseException extends RuntimeException {

    private final HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(Objects.requireNonNull(message, "Exception message must not be null"));
        this.status = Objects.requireNonNull(status, "HttpStatus must not be null");
    }

    public BaseException(String message, Throwable cause, HttpStatus status) {
        super(Objects.requireNonNull(message, "Exception message must not be null"), cause);
        this.status = Objects.requireNonNull(status, "HttpStatus must not be null");
    }
}
