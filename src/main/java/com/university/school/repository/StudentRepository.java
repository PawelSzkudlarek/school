package com.university.school.repository;

import com.university.school.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select s FROM Student s WHERE s.id = :id AND s.person.active = true")
    Student findActiveStudentById(long id);

    @Query("Select s FROM Student s WHERE s.person.active = true")
    List<Student> findAllActiveStudents();
}
