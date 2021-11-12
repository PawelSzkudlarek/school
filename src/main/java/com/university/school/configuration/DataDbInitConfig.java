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
                .lastName("Jahas").city("Gdansk").street("Rdestowa").houseNr("1882").postCode("80-333").mobileNr("123456789").build();

        final StudentForm student2 = StudentForm.builder().login("DamianLogin").name("Damian")
                .lastName("Wasik").city("Morag").street("Kwiatowa").houseNr("162").postCode("80-366").mobileNr("5555555555").build();

        final StudentForm student3 = StudentForm.builder().login("KamilLogin").name("Kamil")
                .lastName("Zdun").city("Olsztyn").street("Ziemniacana Pulpa").houseNr("142").postCode("80-773").mobileNr("666666666").build();

        final StudentForm student4 = StudentForm.builder().login("GlusLogin").name("Chorazy")
                .lastName("Glus").city("Olsztynek").street("zimowa").houseNr("122").postCode("80-388").mobileNr("666666667").build();

        final StudentForm student5 = createStudent("adminek", "Admiral", "Torpeda", "Warszawa", "budowlana", "5", "40-554", "111111111");

        studentRepository.save(EntityMapper.mapFormToEntity(student1));
        studentRepository.save(EntityMapper.mapFormToEntity(student2));
        studentRepository.save(EntityMapper.mapFormToEntity(student3));
        studentRepository.save(EntityMapper.mapFormToEntity(student4));
        studentRepository.save(EntityMapper.mapFormToEntity(student5));
    }

    private StudentForm createStudent(String login, String name, String lastName, String city,
            String street, String houseNr, String postCode, String mobileNo) {
        return StudentForm.builder()
                .login(login)
                .name(name)
                .lastName(lastName)
                .city(city)
                .street(street)
                .houseNr(houseNr)
                .postCode(postCode)
                .mobileNr(mobileNo)
                .build();
    }
}
