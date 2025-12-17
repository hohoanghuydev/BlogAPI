package com.example.blog_api.exception;

import com.example.blog_api.dto.ErrorResponseDto;
import com.example.blog_api.helper.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.blog_api.helper.ErrorMessage.ERROR_INVALID_DATA;

@Slf4j
@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponseDto errorResponse = createErrorResponse(
                ERROR_INVALID_DATA,
                HttpStatus.BAD_REQUEST,
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        log.error("Unexpected exception: {} {}", ex.getClass().getName(), ex.getMessage(), ex);

        ErrorResponseDto errorResponse = createErrorResponse(
                "An unexpected error occurred. Please contact support",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDto> handleApplicationException(ApplicationException ex) {
        ErrorResponseDto errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getHttpStatus(),
                null
        );

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    private ErrorResponseDto createErrorResponse(String message, HttpStatus httpStatus, Map<String, String> errors) {
        return ErrorResponseDto.builder()
                .message(message)
                .errors(errors)
                .statusCode(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
