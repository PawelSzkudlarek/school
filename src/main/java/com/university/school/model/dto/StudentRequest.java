package com.university.school.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private String groupAttendant;
    private String order;
    private String fieldToOrder;
    private String fieldOfStudy;
    private Integer semester;
    private double averageGrade;
}
