package com.university.school.controller;

import com.university.school.model.dto.EmployeeForm;
import com.university.school.model.entity.Employee;
import com.university.school.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/school/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Employee> getTeacher(@RequestParam long id) {
        return ResponseEntity.of(employeeService.findTeacher(id));
    }

    @PostMapping
    public void addTeacher(@Valid @RequestBody EmployeeForm employeeForm) {
        log.info(String.format("receive new student form %s", employeeForm));
        employeeService.saveTeacher(employeeForm);
    }

    @PutMapping
    public ResponseEntity<Void> updateTeacher(@RequestBody Employee employee) {
        employeeService.updateTeacher();
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam long id) {
        employeeService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAllTeachers(){
        log.info("Get all teachers..");
        return ResponseEntity.ok(employeeService.findAllTeachers());
    }
}
