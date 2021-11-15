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
    public static final String T_MOBILE = "T-Mobile";

    public static Teacher mapFormToEntity(TeacherForm form){
        final PersonDetails personDetails = buildPersonDetails(form.getPersonalNumber(), form.getPhoneNo(), form.getCity(), form.getStreet(), form.getHouseNo(), VODAFONE);
        final Person person = buildPerson(form.getName(), form.getLastName(), form.getLogin(), form.getEmail(), PersonType.TEACHER, personDetails);
        return Teacher.builder().person(person).build();
    }

    public static Student mapFormToEntity(StudentForm form){
        final PersonDetails personDetails = buildPersonDetails(
                form.getPersonalNumber(), form.getPhoneNo(), form.getCity(), form.getStreet(), form.getHouseNo(), T_MOBILE);
        final Person person = buildPerson(form.getName(), form.getLastName(), form.getLogin(), form.getEmail(), PersonType.STUDENT, personDetails);
        return Student.builder().person(person).build();
    }

    private static PersonDetails buildPersonDetails(String personalNumber, String phoneNo, String city, String street, String houseNo, String mobileOperator) {
        return PersonDetails.builder()
                .pesel(personalNumber)
                .phoneNumbers(List.of(PhoneNumber.of(phoneNo, mobileOperator)))
                .address(Address.builder()
                        .city(city)
                        .street(street)
                        .houseNo(houseNo)
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


}
