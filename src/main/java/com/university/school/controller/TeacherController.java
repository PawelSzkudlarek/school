package com.university.school.controller;

import com.university.school.model.dto.TeacherForm;
import com.university.school.model.entity.Teacher;
import com.university.school.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/school/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<Teacher> getTeacher(@RequestParam long id) {
        return ResponseEntity.of(teacherService.findTeacher(id));
    }

    @PostMapping
    public void addTeacher(@Valid @RequestBody TeacherForm teacherForm) {
        log.info(String.format("receive new student form %s", teacherForm));
        teacherService.saveTeacher(teacherForm);
    }

    @PutMapping
    public ResponseEntity<Void> updateTeacher(@RequestBody Teacher teacher) {
        teacherService.updateTeacher();
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Teacher>> findAllTeachers(){
        log.info("Get all teachers..");
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }
}
