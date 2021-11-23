package com.university.school.service;

import com.university.school.annotations.Secured;
import com.university.school.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.university.school.security.UserRole.ADMIN;
import static com.university.school.security.UserRole.STUDENT;

@Secured
@Service("Fake")
@AllArgsConstructor
public class FakeUserDaoService implements UserDao {

    List<User> users;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void createUsers() {
        users = new ArrayList<>();
        users.add(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.com")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .authorities(ADMIN.getGrantedAuthorities())
                .build());

        users.add(User.builder()
                .username("pablo")
                .password(passwordEncoder.encode("123"))
                .email("pablo@wp.com")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .authorities(STUDENT.getGrantedAuthorities())
                .build());
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
