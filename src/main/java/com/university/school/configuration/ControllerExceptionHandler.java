package com.university.school.configuration;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.university.school.exception.UniqueLoginException;
import com.university.school.model.dto.ErrorDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    public static final String LOGIN = "login";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> handleValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(mapBindingResultToDto(exception.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(UniqueLoginException.class)
    public ResponseEntity<ErrorDto> handleValidationException(UniqueLoginException exception) {
        return ResponseEntity.badRequest().body(mapExceptionToDto(exception));
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Object> handleValidationException(UnrecognizedPropertyException exception) {
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .field(exception.getPropertyName()).message(exception.getOriginalMessage()).build());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException() {
        return ResponseEntity.notFound().build();
    }

    private List<ErrorDto> mapBindingResultToDto(List<FieldError> errors) {
        return errors.stream()
                .map(error -> ErrorDto.builder().field(error.getField()).message(error.getDefaultMessage()).build())
                .collect(Collectors.toList());
    }

    private ErrorDto mapExceptionToDto(UniqueLoginException exception) {
        return ErrorDto.builder().field(LOGIN)
                .message("Proposed free logins: " + String.join(" , ", exception.getProposals())).build();
    }
}
