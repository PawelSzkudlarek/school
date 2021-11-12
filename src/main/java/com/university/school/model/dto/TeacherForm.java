package com.university.school.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.university.school.valiation.validator.UniqueLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherForm {

    //User
    @NotEmpty
    @UniqueLogin
    private String login;

    @NotEmpty
    @Email
    private String email;

    //Personal Data
    @NotNull
    private String name;
    @NotNull
    private String lastName;

    @NotEmpty
    @Digits(fraction = 0, integer = 11)
    private String personalNumber;

    //Address
    @NotNull
    private String city;
    private String street;
    private String postCode;
    private String houseNo;
    private String apartmentNo;

    @Pattern(regexp = "^([0-9]{9})$")
    private String phoneNo;
}
