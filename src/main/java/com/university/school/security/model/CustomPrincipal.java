package com.university.school.security.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CustomPrincipal {

    private String personId;
    private String username;
}
