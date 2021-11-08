package com.university.school.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IndexSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String schoolSubject;

    private int grade;
}
