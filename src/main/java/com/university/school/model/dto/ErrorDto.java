package com.university.school.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDto {

    private String field;
    private String message;

}
