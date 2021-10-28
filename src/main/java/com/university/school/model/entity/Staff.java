package com.university.school.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.university.school.model.enums.PersonType;
import com.university.school.model.enums.WorkArea;
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
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private WorkArea workArea;

}
