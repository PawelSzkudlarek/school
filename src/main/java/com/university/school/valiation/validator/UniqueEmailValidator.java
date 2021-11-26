package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.school.repository.UserRepository;
import com.university.school.valiation.annotation.UniqueEmail;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(email);
    }
}
