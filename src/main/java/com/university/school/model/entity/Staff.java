package com.university.school.model.entity;


import javax.persistence.Entity;

import com.university.school.model.enums.WorkArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Staff extends Person {

    private boolean active;

    private WorkArea workArea;

}
