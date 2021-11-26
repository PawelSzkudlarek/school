package com.university.school.model.form;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.university.school.valiation.annotation.UniqueEmail;
import com.university.school.valiation.annotation.UniqueUsername;
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

    @Email
    @NotEmpty
    @UniqueEmail
    private String email;

    //User
    @NotEmpty
    @UniqueUsername(hints = {"personalNumber"})
    private String username;
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
