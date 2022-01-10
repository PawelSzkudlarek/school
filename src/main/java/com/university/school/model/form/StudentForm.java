package com.university.school.model.form;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.university.school.model.enums.WorkArea;
import com.university.school.valiation.annotation.UniqueEmail;
import com.university.school.valiation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"name", "lastName", "email", "login", "phoneNo", "personalNumber", "city", "street", "houseNo", "apartmentNo", "postCode"})
public class StudentForm {

    //Personal Data
    @NotNull
    private String name;
    @NotNull
    private String lastName;

//    @Digits(fraction = 0, integer = 11)
    private String personalNumber;
    @Pattern(regexp = "^([0-9]{9})$")
    private String phoneNo;

    //User
    @UniqueUsername
    private String username;
    @Email
    @UniqueEmail
    private String email;
    private String password;

    //Address
    @NotNull
    private String city;
    private String street;
    private String houseNo;
    private String apartmentNo;
    private String postCode;

    private String specialization;
    private int semester;

    private boolean createUser;

}
