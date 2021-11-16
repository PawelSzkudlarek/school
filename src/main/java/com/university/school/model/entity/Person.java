package com.university.school.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.school.model.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "personDetails"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String personalNumber;
    private String phoneNo;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Builder.Default
    private boolean active = true;
}
