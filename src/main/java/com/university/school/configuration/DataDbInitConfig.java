package com.university.school.configuration;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.dto.TeacherForm;
import com.university.school.repository.StudentRepository;
import com.university.school.repository.TeacherRepository;
import com.university.school.service.TeacherService;
import com.university.school.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataDbInitConfig {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @PostConstruct
    void initDbData() {
        getStudents().forEach(studentForm -> studentRepository.save(EntityMapper.mapFormToEntity(studentForm)));
        getTeachers().forEach(teacherForm -> teacherRepository.save(EntityMapper.mapFormToEntity(teacherForm)));
    }

    private List<TeacherForm> getTeachers() {
        TeacherForm teacher1 = TeacherForm.builder().login("DominoLogin").name("Domino")
                .lastName("Jahas").city("Gdansk").street("Rdestowa").houseNo("1882").postCode("80-333").phoneNo("123456789").build();
        TeacherForm teacher2 = TeacherForm.builder().login("DamianLogin").name("Damian")
                .lastName("Wasik").city("Morag").street("Kwiatowa").houseNo("162").postCode("80-366").phoneNo("5555555555").build();
        TeacherForm teacher3 = TeacherForm.builder().login("GlusLogin").name("Chorazy")
                .lastName("Glus").city("Olsztynek").street("zimowa").houseNo("122").postCode("80-388").phoneNo("666666667").build();
        return List.of(teacher1, teacher2, teacher3);
    }

    private List<StudentForm> getStudents() {
        final StudentForm student1 = StudentForm.builder().login("DominoLogin").name("Domino")
                .lastName("Jahas").city("Gdansk").personalNumber("09876543210").street("Rdestowa").houseNo("1882").postCode("80-333").phoneNo("123456789").build();

        final StudentForm student2 = StudentForm.builder().login("DamianLogin").name("Damian")
                .lastName("Wasik").city("Morag").street("Kwiatowa").personalNumber("455678932152").houseNo("162").postCode("80-366").phoneNo("5555555555").build();

        final StudentForm student3 = StudentForm.builder().login("KamilLogin").name("Kamil")
                .lastName("Zdun").city("Olsztyn").street("Ziemniacana Pulpa").personalNumber("45665456546").houseNo("142").postCode("80-773").phoneNo("666666666").build();

        final StudentForm student4 = StudentForm.builder().login("GlusLogin").name("Chorazy")
                .lastName("Glus").city("Olsztynek").street("zimowa").houseNo("122").postCode("80-388").phoneNo("666666667").build();

        final StudentForm student5 = createStudent("adminek", "Admiral", "Torpeda", "Warszawa", "budowlana", "5", "40-554", "111111111");

        return List.of(student1, student2, student3, student4, student5);
    }

    private StudentForm createStudent(String login, String name, String lastName, String city,
                                      String street, String houseNr, String postCode, String mobileNo) {
        return StudentForm.builder()
                .login(login)
                .name(name)
                .lastName(lastName)
                .city(city)
                .street(street)
                .houseNo(houseNr)
                .postCode(postCode)
                .phoneNo(mobileNo)
                .build();
    }
}
