package com.university.school.service;

import javax.annotation.PostConstruct;

import com.university.school.annotations.Secured;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.university.school.security.model.UserRole.ADMIN;
import static com.university.school.security.model.UserRole.STUDENT;

@Secured
@Service("Fake")
@AllArgsConstructor
public class FakeUserDaoService implements UserDao {

    private List<User> users;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @PostConstruct
    private void createUsers() {
        users = new ArrayList<>();
        users.add(User.builder()
                .id(1L)
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .userRole(ADMIN)
                .email("admin@admin.com")
                .person(Person.builder().id(1).build())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build());

        users.add(User.builder()
                .id(2L)
                .person(Person.builder().id(2).build())
                .username("pablo")
                .password(passwordEncoder.encode("123"))
                .email("pablo@wp.com")
                .userRole(STUDENT)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build());
    }
}
