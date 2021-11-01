package com.university.school.controller;

import javax.validation.Valid;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.User;
import com.university.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/school/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<Student> findActiveStudent(@RequestParam long id) {
        return new ResponseEntity<>(studentService.findActiveStudent(id), HttpStatus.OK);
    }

    @GetMapping(headers = "onlyActive=false")
    public ResponseEntity<Student> findStudent(@RequestParam long id) {
        return ResponseEntity.of(studentService.findStudent(id));
    }

    @PostMapping
    public ResponseEntity<User> addStudent(@Valid @RequestBody StudentForm studentForm) {
        log.info(String.format("Receive new student form %s", studentForm));
        return new ResponseEntity<>(studentService.saveStudent(studentForm), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        log.info(String.format("Receive student for update %s", student));
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@RequestParam long id) {
        log.info(String.format("Receive request for delete student with id: %s", id));
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
