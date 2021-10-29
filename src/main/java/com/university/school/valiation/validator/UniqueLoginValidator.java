package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.school.exception.UniqueLoginException;
import com.university.school.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final LoginService loginService;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        if (isNotEmpty(login) && loginService.isUniqueLogin(login)) {
            throw new UniqueLoginException("Provided login " + login + " exists!");
        }
        return true;
    }

    private boolean isNotEmpty(String login) {
        return login != null && !login.trim().equals("");
    }
}
