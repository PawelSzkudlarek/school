package com.university.school.valiation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface UniqueLogin {

    String message() default "Provided login is not unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
