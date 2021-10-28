package com.university.school.service;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Student;
import com.university.school.repository.PersonRepository;
import com.university.school.repository.StudentRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    public Optional<Student> findStudent(long id) {
        final Optional<Student> student = studentRepository.findById(id);
        return student;
    }

    public void saveStudent(StudentForm studentForm) {
        studentRepository.save(EntityMapper.mapFormToEntity(studentForm));
    }

}
