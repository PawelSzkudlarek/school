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

        final StudentForm student4 = StudentForm.builder().login("GlusLogin").name("Chorazy")
                .lastName("Glus").city("Olsztynek").mobileNr("666666667").build();

        createStudent("adminek","Admiral", "Torpeda", "Warszawa", "111111111");

        studentRepository.save(EntityMapper.mapFormToEntity(student1));
        studentRepository.save(EntityMapper.mapFormToEntity(student2));
        studentRepository.save(EntityMapper.mapFormToEntity(student3));
        studentRepository.save(EntityMapper.mapFormToEntity(student4));
        studentRepository.save(EntityMapper.mapFormToEntity(createStudent("adminek","Admiral", "Torpeda", "Warszawa", "111111111")));
    }

    private StudentForm createStudent(String login, String name, String lastName, String city, String mobileNo) {
        return StudentForm.builder()
                .login(login)
                .name(name)
                .lastName(lastName)
                .city(city)
                .mobileNr(mobileNo)
                .build();
    }
}
