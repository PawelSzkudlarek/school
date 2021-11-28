package com.university.school.model.form;

import com.university.school.model.enums.WorkArea;
import com.university.school.valiation.annotation.UniqueEmail;
import com.university.school.valiation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

    //Personal Data
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
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

    private WorkArea workArea;
    private boolean createUser;
}
