package com.university.school.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                String.format("Validation failed on field '%s', in reason: %s",
                        exception.getFieldError().getField(), exception.getFieldError().getDefaultMessage()));
    }
}
