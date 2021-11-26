package com.university.school.repository;

import com.university.school.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u.username from User u where u.username like :username")
    Set<String> findUsernamesLike(String username);
}
