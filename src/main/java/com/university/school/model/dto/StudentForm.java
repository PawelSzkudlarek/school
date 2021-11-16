package com.university.school.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.university.school.valiation.validator.UniqueLogin;
import lombok.*;

import javax.validation.constraints.*;

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
    @NotEmpty
    @Email
    private String email;

    //User
    @NotEmpty
    @UniqueLogin(hints = {"personalNumber"})
    private String login;
    private String password;

    @Pattern(regexp = "^([0-9]{9})$")
    private String phoneNo;

    @NotEmpty
    @Digits(fraction = 0, integer = 11)
    private String personalNumber;

    private int semester;
    //Address
    @NotNull
    private String city;
    private String street;
    private String houseNo;
    private String apartmentNo;
    private String postCode;
}
