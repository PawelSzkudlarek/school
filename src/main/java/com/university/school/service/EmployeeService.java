package com.university.school.service;

import com.university.school.model.dto.EmployeeForm;
import com.university.school.model.entity.Employee;
import com.university.school.repository.EmployeeRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Optional<Employee> findTeacher(long id) {
        return Optional.ofNullable(employeeRepository.findActiveTeacher(id));
    }

    public void saveTeacher(EmployeeForm employeeForm) {
        employeeRepository.save(EntityMapper.mapFormToEntity(employeeForm));
    }

    public void deleteTeacher(long id) {
        throw new NotImplementedException();
    }

    public void updateTeacher() {
        throw new NotImplementedException();
    }

    public List<Employee> findAllTeachers() {
        return employeeRepository.findAll();
    }
}
