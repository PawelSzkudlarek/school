package com.university.school.model.dto;

import com.university.school.model.enums.WorkArea;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDto {

    private long id;
    //Personal Data
    private String name;
    private String lastName;
    //    @Digits(fraction = 0, integer = 11)
    private String personalNumber;
    private String phoneNo;

    //User
    private String username;
    private String email;
    private String password;

    //Address
    private String city;
    private String street;
    private String houseNo;
    private String apartmentNo;
    private String postCode;

    private WorkArea workArea;
}
