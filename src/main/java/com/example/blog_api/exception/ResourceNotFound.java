package com.example.blog_api.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends ApplicationException {
    public ResourceNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
