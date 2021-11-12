package com.university.school.controller;

import javax.validation.Valid;

import com.university.school.model.dto.TeacherForm;
import com.university.school.model.entity.Teacher;
import com.university.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/school/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private TeacherService teacherService;

    @GetMapping("/findTeacherById")
    public ResponseEntity<Teacher> getTeacher(@RequestParam long id) {
        return ResponseEntity.of(teacherService.findTeacher(id));
    }

    @PostMapping("/addStudent")
    public void addStudent(@Valid @RequestBody TeacherForm teacherForm) {
        log.info(String.format("receive new student form %s", teacherForm));
        teacherService.saveTeacher(teacherForm);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
