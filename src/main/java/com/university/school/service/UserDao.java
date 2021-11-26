package com.university.school.service;

import com.university.school.model.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
