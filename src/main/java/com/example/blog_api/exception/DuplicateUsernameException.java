package com.example.blog_api.exception;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApplicationException {
    public DuplicateUsernameException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
