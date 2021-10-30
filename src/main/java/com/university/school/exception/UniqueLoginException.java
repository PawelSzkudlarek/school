package com.university.school.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UniqueLoginException extends RuntimeException {

    private final String message;
    private final Set<String> proposals;

}
