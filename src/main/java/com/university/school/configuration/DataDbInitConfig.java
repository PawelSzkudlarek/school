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
        final StudentForm student1 = StudentForm.builder().login("DominoLogin").name("Domino")
                .lastName("Jahas").city("Gdansk").mobileNr("123456789").build();

        final StudentForm student2 = StudentForm.builder().login("DamianLogin").name("Damian")
                .lastName("Wasik").city("Morag").mobileNr("5555555555").build();

        final StudentForm student3 = StudentForm.builder().login("KamilLogin").name("Kamil")
                .lastName("Zdun").city("Olsztyn").mobileNr("666666666").build();

        studentRepository.save(EntityMapper.mapFormToEntity(student1));
        studentRepository.save(EntityMapper.mapFormToEntity(student2));
        studentRepository.save(EntityMapper.mapFormToEntity(student3));
    }
}
