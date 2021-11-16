package com.university.school.repository;

import com.university.school.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select s FROM Employee s WHERE s.id = :id AND s.workArea = 'TEACHER' AND s.person.active = true")
    Employee findActiveTeacher(long id);

}
