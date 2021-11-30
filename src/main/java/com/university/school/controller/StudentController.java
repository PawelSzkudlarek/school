package com.university.school.controller;

import javax.validation.Valid;

import com.university.school.annotations.Secured;
import com.university.school.model.dto.StudentDetailsDto;
import com.university.school.model.dto.StudentDto;
import com.university.school.model.form.StudentForm;
import com.university.school.model.dto.StudentRequest;
import com.university.school.model.dto.UserDto;
import com.university.school.model.entity.Student;
import com.university.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured
@Log4j2
@RestController
@RequestMapping("/school/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(produces = {"application/json", "application/xml"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER', 'ROLE_EMPLOYEE') or principal.personId == #id")
    public ResponseEntity<StudentDto> findActiveStudent(@RequestParam String id) {
        return new ResponseEntity<>(studentService.findActiveStudent(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.personId == #id")
    public ResponseEntity<UserDto> addStudent(@Valid @RequestBody StudentForm studentForm) {
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.personId == #id")
    @GetMapping("/details")
    public ResponseEntity<StudentDetailsDto> getPersonalDetails(@RequestParam String id) {
        log.info("Get student details by id:" + id);
        return ResponseEntity.of(studentService.getStudentDetails(Long.parseLong(id)));
    }
}
