package com.university.school.valiation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.university.school.valiation.validator.UniqueEmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "Provided email is not unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


