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
public class UserAuthority implements GrantedAuthority {

    // Change that to persmission
    public UserAuthority(String authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue
    private long id;

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
