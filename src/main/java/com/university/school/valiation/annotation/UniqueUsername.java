package com.university.school.valiation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.university.school.valiation.validator.UniqueUsernameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface UniqueUsername {

    String message() default "Provided login is not unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] hints() default {};
}
