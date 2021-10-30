package com.university.school.repository;


import com.university.school.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LoginRepository extends JpaRepository<Login, Long> {

    boolean existsByValue(String login);

    Set<Login> findByValueLike(String login);
}
