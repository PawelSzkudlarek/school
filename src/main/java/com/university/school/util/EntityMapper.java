package com.university.school.util;

import com.university.school.model.dto.EmployeeForm;
import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Address;
import com.university.school.model.entity.Employee;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.User;
import com.university.school.model.enums.PersonType;
import com.university.school.model.enums.WorkArea;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.university.school.security.model.UserRole.EMPLOYEE;
import static com.university.school.security.model.UserRole.STUDENT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityMapper {

    public static Employee mapFormToEntity(EmployeeForm form) {
        return mapFormToEntity(form, WorkArea.TEACHER);
    }

    private static Employee mapFormToEntity(EmployeeForm form, WorkArea workArea) {
        return Employee.builder()
                .person(Person.builder()
                        .name(form.getName())
                        .lastName(form.getLastName())
                        .personalNumber(form.getPersonalNumber())
                        .phoneNo(form.getPhoneNo())
//                        .user(User.builder()
//                                .username(form.getLogin())
//                                .password(form.getPassword())
//                                .userRole(EMPLOYEE)
//                                .build())
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

    public static Student mapFormToEntity(StudentForm form) {
        return Student.builder()
                .person(Person.builder()
                        .name(form.getName())
                        .lastName(form.getLastName())
                        .personalNumber(form.getPersonalNumber())
                        .phoneNo(form.getPhoneNo())
//                        .user(User.builder()
//                                .username(form.getLogin())
//                                .password(form.getPassword())
//                                .userRole(STUDENT)
//                                .build())
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
