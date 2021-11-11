package com.university.school.model.dto;

import com.university.school.model.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StudentDetailsDto {

    private String name;
    private String lastName;
    private int semester;
    private String pesel;
    private List<String> phoneNumbers;
    private Address address;

}
