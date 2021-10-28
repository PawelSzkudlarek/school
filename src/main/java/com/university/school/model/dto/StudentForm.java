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

    @NotNull
    @UniqueLogin
    private String login;

    //Personal Data
    @NotNull
    private String name;
    @NotNull
    private String lastName;

    @Size
    @Min(11)
    @Max(11)
    private String personalNumber;

    //Address
    @NotNull
    private String city;
    private String street;
    private String postCode;
    private String houseNr;
    private String apartmentNr;

    @Pattern(regexp = "^([0-9]{9})$")
    private String mobileNr;
}