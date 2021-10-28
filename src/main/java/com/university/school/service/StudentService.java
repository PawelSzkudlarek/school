package com.university.school.service;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Student;
import com.university.school.repository.StudentRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Student findActiveStudent(long id) {
        return studentRepository.findActiveStudentById(id);
    }

    public Student saveStudent(StudentForm studentForm) {
        return studentRepository.save(EntityMapper.mapFormToEntity(studentForm));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);

    }
}
