package com.university.school.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements GrantedAuthority {

    public Permission(String permission) {
        this.permission = permission;
    }

    @Id
    @GeneratedValue
    private long id;

    private String permission;

    @Override
    public String getAuthority() {
        return this.permission;
    }
}
