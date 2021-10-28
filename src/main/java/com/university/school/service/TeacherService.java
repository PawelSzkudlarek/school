package com.university.school.service;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {


    public Optional<Teacher> findTeacher(long id) {
        return Optional.empty();
    }

    public void saveTeacher(StudentForm studentForm) {
    }
}
