package com.university.school.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.university.school.valiation.annotation.UniqueLogin;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class StudentDto {

    @UniqueLogin
    private String login;

    //Personal Data
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @Pattern(regexp = "^([0-9]{4})$")
    private String personalId;

    //Address
    private String city;
    private String street;
    private String postCode;
    private String houseNr;
    private String apartmentNr;

    @Override
    public String toString() {
        return "StudentDto{" +
                "uniqueLogin='" + login + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }
}
