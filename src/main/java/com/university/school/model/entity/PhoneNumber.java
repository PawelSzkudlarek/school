package com.university.school.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;

    private String mobileOperator;

    public PhoneNumber(String number, String mobileOperator) {
        this.number = number;
        this.mobileOperator = mobileOperator;
    }

    public static PhoneNumber of(String phoneNo, String mobileOperator){
        return new PhoneNumber(phoneNo, mobileOperator);
    }
}
