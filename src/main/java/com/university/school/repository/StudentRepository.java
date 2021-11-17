package com.university.school.repository;

import com.university.school.model.entity.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select s FROM Student s WHERE s.id = :id AND s.person.active = true")
    Student findActiveStudentById(long id);

    @Query("Select s FROM Student s WHERE s.person.active = true")
    List<Student> findAllActiveStudents(PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("Update Person p SET active = false WHERE id =" +
            " (select s.person.id from Student s where s.id = :id)")
    void setStudentToInactive(long id);
}
