package com.university.school.service;

import com.university.school.model.dto.TeacherForm;
import com.university.school.model.entity.Teacher;
import com.university.school.repository.TeacherRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Optional<Teacher> findTeacher(long id) {
        return teacherRepository.findByIdAndStatusActive(id);
    }

    public void saveTeacher(TeacherForm teacherForm) {
        teacherRepository.save(EntityMapper.mapFormToEntity(teacherForm));
    }

    public void deleteTeacher(long id) {

    }

}
