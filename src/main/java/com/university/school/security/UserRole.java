package com.university.school.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum UserRole {
    STUDENT(List.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
    EMPLOYEE(List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))),
    ADMIN(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

    private final List<GrantedAuthority> authorities;

    UserRole(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return this.authorities;
    }
}
