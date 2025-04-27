package com.djess.auth.user.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message, // for business logic exceptions (e.g. 404, 400, 409)
        List<String> messages, // for validation exceptions only
        String path
) {
    public ErrorResponse {
        Objects.requireNonNull(path, "Path must not be null");
        Objects.requireNonNull(timestamp, "Timestamp must not be null");
        Objects.requireNonNull(error, "Error must not be null");

        if (message == null && (messages == null || messages.isEmpty())) {
            throw new IllegalArgumentException("Either 'message' or non-empty 'messages' must be provided.");
        }
        if (message != null && messages != null) {
            throw new IllegalArgumentException("Only one of 'message' or 'messages' must be provided, not both.");
        }

    }
}

