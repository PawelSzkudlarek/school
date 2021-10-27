package com.university.school.service;

import com.university.school.model.dto.StudentDto;
import com.university.school.model.entity.Login;
import com.university.school.model.entity.PersonalDetails;
import com.university.school.model.entity.Student;
import com.university.school.repository.StudentRepository;
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

    public void saveStudent(StudentDto studentDto) {
        studentRepository.save(mapToEntity(studentDto));
    }

    private Student mapToEntity(StudentDto studentDto) {
        final Student student = new Student();
        student.setLogin(Login.of(studentDto.getLogin()));
        student.setPersonalDetails(new PersonalDetails());
        return student;
    }
}
