package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.university.school.exception.UniqueLoginException;
import com.university.school.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
@AllArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final LoginService loginService;
    private List<String> hints;
    private String fieldName;

    @Override
    public void initialize(UniqueLogin login){
        this.hints = Arrays.asList(login.hints());
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        if (loginService.isLoginExists(login)) {
            throw new UniqueLoginException("Provided login " + login + " exists!", loginService.createProposals(login));
        }
        return true;
    }

    private boolean isNotEmpty(String login) {
        return login != null && !login.trim().equals("");
    }
}
