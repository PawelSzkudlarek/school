package com.university.school.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Student extends Person {

    private boolean active;

    @OneToOne(mappedBy = "student")
    private StudentIndex studentIndex;

    @ManyToMany(mappedBy = "students")
    private List<UniversityGroup> universityGroups;

}
