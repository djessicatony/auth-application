package com.djess.auth.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ErrorResponseMapper {

    public ErrorResponse toErrorResponse(BaseException ex, String path) {
        return new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(),
                ex.getMessage(),
                null,
                path
        );
    }

    public ErrorResponse toValidationErrorResponse(MethodArgumentNotValidException ex, String path) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error ->error.getField() + ": " + error.getDefaultMessage())
                                .toList();

        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                null,
                errors,
                path
        );
    }
}
