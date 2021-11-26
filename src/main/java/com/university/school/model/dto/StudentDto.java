package com.university.school.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StudentDto {
    private String name;
    private String lastName;
    private String email;
    private boolean active;
    private int semester;
    private String fieldOfStudy;
}
