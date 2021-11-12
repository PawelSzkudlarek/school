package com.university.school.util;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.dto.TeacherForm;
import com.university.school.model.entity.Address;
import com.university.school.model.entity.Login;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.PersonDetails;
import com.university.school.model.entity.PhoneNumber;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.Teacher;
import com.university.school.model.enums.PersonType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityMapper {

    public static final String VODAFONE = "Vodafone";

    public static Teacher mapFormToEntity(TeacherForm form){
        final PersonDetails personDetails = buildPersonDetails(form.getPersonalNumber(), form.getPhoneNo(), form.getCity(), form.getStreet(), form.getHouseNo());
        final Person person = buildPerson(form.getName(), form.getLastName(), form.getLogin(), form.getEmail(), PersonType.TEACHER, personDetails);
        return Teacher.builder().person(person).build();
    }

    private static PersonDetails buildPersonDetails(String personalNumber, String phoneNo, String city, String street, String houseNo) {
        return PersonDetails.builder()
                .pesel(personalNumber)
                .phoneNumbers(List.of(PhoneNumber.of(phoneNo, VODAFONE)))
                .address(Address.builder()
                        .city(city)
                        .street(street)
                        .houseNr(houseNo)
                        .build())
                .build();

    }

    private static Person buildPerson(String name, String lastName, String login,
                                      String email, PersonType personType, PersonDetails personDetails) {
        return Person.builder()
                .name(name)
                .lastName(lastName)
                .login(Login.of(login))
                .email(email)
                .personType(personType)
                .personDetails(personDetails)
                .build();
    }

    public static Student mapFormToEntity(StudentForm studentForm) {
        return Student.builder()
                .person(Person.builder()
                        .name(studentForm.getName())
                        .lastName(studentForm.getLastName())
                        .login(Login.of(studentForm.getLogin()))
                        .email(studentForm.getEmail())
                        .personType(PersonType.STUDENT)
                        .personDetails(PersonDetails.builder()
                                .address(Address.builder()
                                        .city(studentForm.getCity())
                                        .street(studentForm.getStreet())
                                        .build())
                                .build())
                        .build())
                .build();
    }


}
