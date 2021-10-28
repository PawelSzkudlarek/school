package com.university.school.controller;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.Teacher;
import com.university.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/school/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private TeacherService teacherService;

    @GetMapping("/findTeacherById")
    public ResponseEntity<Teacher> getTeacher(@RequestParam long id) {
        return ResponseEntity.of(teacherService.findTeacher(id));
    }

    @PostMapping("/addStudent")
    public void addStudent(@Valid @RequestBody StudentForm studentForm){
        log.info(String.format("receive new student form %s", studentForm));
        teacherService.saveTeacher(studentForm);
    }
}
