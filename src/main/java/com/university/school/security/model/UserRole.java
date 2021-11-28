package com.university.school.security.model;

import com.university.school.model.enums.WorkArea;

import java.util.List;
import java.util.stream.Collectors;

import static com.university.school.security.model.UserPermission.*;

public enum UserRole {

    STUDENT("ROLE_STUDENT", List.of()),
    EMPLOYEE("ROLE_EMPLOYEE", List.of(STUDENT_READ)),
    TEACHER("ROLE_TEACHER", List.of(STUDENT_READ, STUDENT_WRITE)),
    ADMIN("ROLE_ADMIN", List.of(STUDENT_READ, STUDENT_WRITE, EMPLOYEE_READ, EMPLOYEE_WRITE));

    private final String role;
    private final List<UserPermission> permissions;

    UserRole(String role, List<UserPermission> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public List<String> getGrantedAuthorities() {
        final List<String> permissionList =
                permissions.stream().map(UserPermission::getValue).collect(Collectors.toList());
        permissionList.add(role);
        return permissionList;
    }

    public static UserRole findProperRole(WorkArea workArea) {
        switch (workArea) {
            case TEACHER:
                return UserRole.TEACHER;
            default:
                throw new RuntimeException("");
        }
    }


}
