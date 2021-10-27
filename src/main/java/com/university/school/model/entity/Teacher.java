package com.university.school.model.entity;


import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher extends Person {

    private boolean active;

    private String mainSubject;
}
