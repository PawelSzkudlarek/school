package com.university.school.service;

import com.university.school.model.dto.EmployeeDetailsDto;
import com.university.school.model.entity.Employee;
import com.university.school.model.entity.User;
import com.university.school.model.form.EmployeeForm;
import com.university.school.repository.EmployeeRepository;
import com.university.school.security.model.UserRole;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Optional<Employee> findEmployeeById(long id) {
        return Optional.ofNullable(employeeRepository.findActiveTeacher(id));
    }

    public void saveEmployee(EmployeeForm form) {
        final Employee employee = EntityMapper.mapFormToEntity(form);
        if (form.isCreateUser()) {
            employee.getPerson().setUser(User.builder()
                    .username(form.getUsername())
                    .password(form.getPassword())
                    .userRole(UserRole.findProperRole(form.getWorkArea()))
                    .build());
        }
        employeeRepository.save(employee);
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

    public void updateEmployee(EmployeeDetailsDto dto) {
        employeeRepository
                .findById(dto.getId())
                .ifPresentOrElse(employee -> {
                    employee.getPerson().setName(dto.getName());
                    employee.getPerson().setLastName(dto.getLastName());
                    employee.getPerson().setPersonalNumber(dto.getPersonalNumber());
                    employee.getPerson().setPhoneNo(dto.getPhoneNo());
                    employee.getPerson().getAddress().setCity(dto.getCity());
                    employee.getPerson().getAddress().setStreet(dto.getStreet());
                    employee.getPerson().getAddress().setHouseNo(dto.getHouseNo());
                    employee.getPerson().getAddress().setApartmentNo(dto.getApartmentNo());
                    employee.getPerson().getAddress().setPostCode(dto.getPostCode());
                    employee.setWorkArea(dto.getWorkArea());
                    employeeRepository.save(employee);
                }, () -> new RuntimeException("Employee not exists."));
    }

    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAllActiveEmployees(pageable);
    }

    public Optional<EmployeeDetailsDto> getEmployeeDetails(long id) {
        return employeeRepository.findById(id)
                .map(this::mapEmployeeToDto)
                .or(Optional::empty);
    }

    private EmployeeDetailsDto mapEmployeeToDto(Employee employee) {
        return EmployeeDetailsDto.builder()
                .id(employee.getId())
                .name(employee.getPerson().getName())
                .lastName(employee.getPerson().getLastName())
                .personalNumber(employee.getPerson().getPersonalNumber())
                .phoneNo(employee.getPerson().getPhoneNo())
                .city(employee.getPerson().getAddress().getCity())
                .street(employee.getPerson().getAddress().getStreet())
                .houseNo(employee.getPerson().getAddress().getHouseNo())
                .apartmentNo(employee.getPerson().getAddress().getApartmentNo())
                .postCode(employee.getPerson().getAddress().getPostCode())
                .workArea(employee.getWorkArea())
                .build();
    }
}
