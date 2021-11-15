package com.university.school.repository;

import com.university.school.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("Select s FROM Teacher s WHERE s.id = :id AND s.person.active = true")
    Teacher findActiveTeacher(long id);

}
