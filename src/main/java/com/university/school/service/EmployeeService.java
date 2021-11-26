package com.university.school.service;

import com.university.school.model.form.EmployeeForm;
import com.university.school.model.entity.Employee;
import com.university.school.repository.EmployeeRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(long id) {
        return Optional.ofNullable(employeeRepository.findActiveTeacher(id));
    }

    public void saveEmployee(EmployeeForm employeeForm) {
        employeeRepository.save(EntityMapper.mapFormToEntity(employeeForm));
    }

    public HttpStatus deleteEmployee(long id) {
        final Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(emp -> {
            emp.getPerson().setActive(false);
            employeeRepository.save(emp);
            log.info(String.format("Employee with id %s was deleted", id));
            return HttpStatus.ACCEPTED;
        }).orElse(HttpStatus.NOT_FOUND);
    }

    public void updateEmployee() {
        throw new NotImplementedException();
    }

    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAllActiveEmployees(pageable);
    }
}
