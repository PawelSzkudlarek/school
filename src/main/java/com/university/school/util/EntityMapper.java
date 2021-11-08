package com.university.school.util;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Address;
import com.university.school.model.entity.Login;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.PersonDetails;
import com.university.school.model.entity.Student;
import com.university.school.model.enums.PersonType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityMapper {

    public static Student mapFormToEntity(StudentForm studentForm) {
        return Student.builder()
                .person(Person.builder()
                        .login(Login.of(studentForm.getLogin()))
                        .email(studentForm.getEmail())
                        .personType(PersonType.STUDENT)
                        .name(studentForm.getName())
                        .lastName(studentForm.getLastName())
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
