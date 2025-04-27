package com.djess.auth.user.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = errorResponseMapper.toErrorResponse(ex, request.getRequestURI());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = errorResponseMapper.toErrorResponse(ex, request.getRequestURI());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    // handling default exceptions caused by @Valid / @NotBlank, not a custom exception.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        ErrorResponse errorResponse = errorResponseMapper.toValidationErrorResponse(ex, request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
