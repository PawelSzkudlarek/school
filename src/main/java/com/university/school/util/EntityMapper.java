package com.university.school.util;

import com.university.school.model.dto.StudentForm;
import com.university.school.model.dto.EmployeeForm;
import com.university.school.model.entity.*;
import com.university.school.model.enums.PersonType;
import com.university.school.model.enums.WorkArea;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityMapper {


    public static Employee mapFormToTeacherEntity(EmployeeForm form){
        return mapFormToEntity(form, WorkArea.TEACHER);
    }

    private static Employee mapFormToEntity(EmployeeForm form, WorkArea workArea){
        return Employee.builder()
                .person(Person.builder()
                        .name(form.getName())
                        .lastName(form.getLastName())
                        .personalNumber(form.getPersonalNumber())
                        .phoneNo(form.getPhoneNo())
                        .user(new User(form.getLogin(), form.getPassword(), form.getEmail()))
                        .email(form.getEmail())
                        .address(Address.builder()
                                .city(form.getCity())
                                .street(form.getStreet())
                                .houseNo(form.getHouseNo())
                                .apartmentNr(form.getApartmentNo())
                                .postCode(form.getPostCode())
                                .build())
                        .personType(PersonType.EMPLOYEE)
                        .build())
                .workArea(workArea)
                .build();
    }

    public static Student mapFormToEntity(StudentForm form){
        return Student.builder()
                .person(Person.builder()
                        .name(form.getName())
                        .lastName(form.getLastName())
                        .personalNumber(form.getPersonalNumber())
                        .phoneNo(form.getPhoneNo())
                        .user(new User(form.getLogin(), form.getPassword(), form.getEmail()))
                        .email(form.getEmail())
                        .personType(PersonType.STUDENT)
                        .address(Address.builder()
                                .city(form.getCity())
                                .street(form.getStreet())
                                .houseNo(form.getHouseNo())
                                .apartmentNr(form.getApartmentNo())
                                .postCode(form.getPostCode())
                                .build())
                        .build())
                .semester(form.getSemester())
                .build();
    }
}
