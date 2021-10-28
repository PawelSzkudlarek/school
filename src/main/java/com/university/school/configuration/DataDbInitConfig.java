package com.university.school.configuration;

import javax.annotation.PostConstruct;

import com.university.school.model.dto.StudentForm;
import com.university.school.repository.StudentRepository;
import com.university.school.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataDbInitConfig {

    private final StudentRepository studentRepository;

    @PostConstruct
    void initDbData() {
        final StudentForm student = StudentForm.builder()
                .login("PabloLogin")
                .name("Pablo")
                .lastName("Szk")
                .city("Gdansk")
                .mobileNr("123456789")
                .build();
        studentRepository.save(EntityMapper.mapFormToEntity(student));
    }
}
