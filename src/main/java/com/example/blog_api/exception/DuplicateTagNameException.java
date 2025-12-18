package com.example.blog_api.exception;

import org.springframework.http.HttpStatus;

public class DuplicateTagNameException extends ApplicationException {
    public DuplicateTagNameException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
