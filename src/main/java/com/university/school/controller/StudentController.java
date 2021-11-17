package com.university.school.controller;

import javax.validation.Valid;

import com.university.school.model.dto.StudentDetailsDto;
import com.university.school.model.dto.StudentForm;
import com.university.school.model.dto.StudentRequest;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.User;
import com.university.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(headers = "onlyActive=false")
    public ResponseEntity<Student> findStudent(@RequestParam long id) {
        return ResponseEntity.of(studentService.findStudent(id));
    }

//    @GetMapping("/findAll")
//    public ResponseEntity<List<Student>> findAllStudent(
//            @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String sortby) {
//        log.info("Get all students.. by request");
//        return ResponseEntity.ok(studentService.findAllStudent(page, size, sortby));
//    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<Student>> findAllStudent(Pageable pageable) {
        log.info("Get all students.. by page");
        return ResponseEntity.ok(studentService.findAllStudent(pageable));
    }

    @GetMapping("/request")
    public ResponseEntity<List<Student>> findStudents(@RequestBody StudentRequest request) {
        return studentService.findStudents(request);
    }

    @GetMapping("/details")
    public ResponseEntity<StudentDetailsDto> getPersonalDetails(@RequestParam long id) {
        log.info("Get student detils by id:" + id);
        return ResponseEntity.of(studentService.getStudentDetails(id));
    }
}
