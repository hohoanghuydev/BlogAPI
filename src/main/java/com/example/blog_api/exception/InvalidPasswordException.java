package com.example.blog_api.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ApplicationException {
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
