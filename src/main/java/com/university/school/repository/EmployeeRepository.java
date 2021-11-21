package com.university.school.repository;

import com.university.school.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select s FROM Employee s WHERE s.id = :id AND s.workArea = 'TEACHER' AND s.person.active = true")
    Employee findActiveTeacher(long id);

    @Query("Select s FROM Employee s WHERE s.person.active = true")
    Page<Employee> findAllActiveEmployees(Pageable pageable);
}
