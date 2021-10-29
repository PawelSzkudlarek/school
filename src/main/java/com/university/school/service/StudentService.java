package com.university.school.service;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.User;
import com.university.school.repository.StudentRepository;
import com.university.school.repository.UserRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Student findActiveStudent(long id) {
        return studentRepository.findActiveStudentById(id);
    }

    public User saveStudent(StudentForm studentForm) {
        Person student =  studentRepository.save(EntityMapper.mapFormToEntity(studentForm)).getPerson();
        return userRepository.save(new User(student.getLogin().getValue(), generatePassword(), student.getEmail()));
    }

    private String generatePassword() {
        return RandomStringUtils.random(10, true, false);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);

    }
}
