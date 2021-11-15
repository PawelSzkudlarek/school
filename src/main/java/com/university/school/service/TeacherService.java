package com.university.school.service;

import com.university.school.model.dto.TeacherForm;
import com.university.school.model.entity.Teacher;
import com.university.school.repository.TeacherRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public Optional<Teacher> findTeacher(long id) {
        return Optional.ofNullable(teacherRepository.findActiveTeacher(id));
    }

    public void saveTeacher(TeacherForm teacherForm) {
        teacherRepository.save(EntityMapper.mapFormToEntity(teacherForm));
    }

    public void deleteTeacher(long id) {
        throw new NotImplementedException();
    }

    public void updateTeacher() {
        throw new NotImplementedException();
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }
}
