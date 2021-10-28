package com.university.school.controller;

import javax.validation.Valid;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Student;
import com.university.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/school/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/findStudentById")
    public ResponseEntity<Student> getStudent(@RequestParam long id) {
        return ResponseEntity.of(studentService.findStudent(id));
    }

    @PostMapping("/addStudent")
    public void addStudent(@Valid @RequestBody StudentForm studentForm){
        log.info(String.format("receive new student form %s", studentForm));
        studentService.saveStudent(studentForm);
    }

}
