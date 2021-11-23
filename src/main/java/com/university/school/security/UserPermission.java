package com.university.school.security;

import lombok.Getter;

public enum UserPermission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");

    @Getter
    private final String value;

    UserPermission(String value) {
        this.value = value;
    }
}
