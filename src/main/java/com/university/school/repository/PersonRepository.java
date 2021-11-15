package com.university.school.repository;

import com.university.school.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("Update Person p SET active = false WHERE id =" +
            " (select s.person.id from Student s where s.id = :id)")
    void setStudentToInactive(long id);
}
