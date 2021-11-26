package com.university.school.util;

import com.university.school.model.form.EmployeeForm;
import com.university.school.model.dto.StudentDto;
import com.university.school.model.form.StudentForm;
import com.university.school.model.dto.UserDto;
import com.university.school.model.entity.Address;
import com.university.school.model.entity.Employee;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.Student;
import com.university.school.model.entity.User;
import com.university.school.model.enums.PersonType;
import com.university.school.model.enums.WorkArea;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
                        .user(User.builder()
                                .username(form.getUsername())
                                .password(form.getPassword())
                                .email(form.getEmail())
                                .userRole(STUDENT)
                                .build())
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

    public static UserDto mapToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .username(user.getEmail())
                .build();
    }

    public static StudentDto mapToDto(Student student){
        return StudentDto.builder()
                .name(student.getPerson().getName())
                .lastName(student.getPerson().getLastName())
                .email(student.getPerson().getUser().getEmail())
                .active(student.getPerson().isActive())
                .semester(student.getSemester())
                .fieldOfStudy(student.getFieldOfStudy())
                .build();
    }
}
