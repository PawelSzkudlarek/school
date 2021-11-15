package com.university.school.model.dto;

import javax.validation.constraints.*;

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
public class StudentForm {

    //User
    @NotEmpty
    @UniqueLogin(hints = {"personalNumber"})
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
