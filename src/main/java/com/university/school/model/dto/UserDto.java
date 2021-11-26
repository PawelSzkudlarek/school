package com.university.school.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserDto {

    private String username;
    private String email;
}
