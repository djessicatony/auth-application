package com.djess.auth.user.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BaseException {

    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + (email != null ? email : "unknown"), HttpStatus.CONFLICT);
    }
}
