package com.university.school.controller;

import com.university.school.model.dto.EmployeeDetailsDto;
import com.university.school.model.dto.StudentDetailsDto;
import com.university.school.model.form.EmployeeForm;
import com.university.school.model.entity.Employee;
import com.university.school.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/school/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Employee> getEmployee(@RequestParam long id) {
        return ResponseEntity.of(employeeService.findEmployeeById(id));
    }

    @PostMapping
    public void addEmployee(@Valid @RequestBody EmployeeForm employeeForm) {
        log.info(String.format("receive new student form %s", employeeForm));
        employeeService.saveEmployee(employeeForm);
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDetailsDto employeeDetailsDto) {
        employeeService.updateEmployee(employeeDetailsDto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestParam long id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<Employee>> findAllEmployee(Pageable pageable) {
        log.info("Get all employees.. Page: " + pageable.getPageNumber() + " size: " + pageable.getPageSize());
        final Page<Employee> allEmployee = employeeService.findAllEmployee(pageable);
        System.out.println("total elements: " + allEmployee.getTotalElements());
        return ResponseEntity.ok(allEmployee);
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.personId == #id")
    @GetMapping("/details")
    public ResponseEntity<EmployeeDetailsDto> getPersonalDetails(@RequestParam String id) {
        log.info("Get student details by id:" + id);
        return ResponseEntity.of(employeeService.getEmployeeDetails(Long.parseLong(id)));
    }
}
