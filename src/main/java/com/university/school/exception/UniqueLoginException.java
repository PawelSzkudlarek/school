package com.university.school.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UniqueLoginException extends RuntimeException{

    private final String message;
    private final List<String> proposals;

}
