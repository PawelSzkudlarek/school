package com.university.school.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.university.school.model.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    private String mainSubject;
}
