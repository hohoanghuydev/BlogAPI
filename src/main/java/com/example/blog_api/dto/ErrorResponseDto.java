package com.example.blog_api.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ErrorResponseDto {
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private int statusCode;
    private Map<String, String> errors;
}
