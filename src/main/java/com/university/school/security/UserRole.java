package com.university.school.security;

import com.university.school.model.entity.UserAuthority;

import java.util.List;

public enum UserRole {
    STUDENT(List.of(new UserAuthority("ROLE_STUDENT"))),
    EMPLOYEE(List.of(new UserAuthority("ROLE_EMPLOYEE"))),
    ADMIN(List.of(new UserAuthority("ROLE_ADMIN")));

    private final List<UserAuthority> authorities;

    UserRole(List<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<UserAuthority> getGrantedAuthorities() {
        return this.authorities;
    }
}
