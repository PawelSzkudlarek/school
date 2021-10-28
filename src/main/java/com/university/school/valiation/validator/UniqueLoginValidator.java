package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.school.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final LoginService loginService;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return loginService.isUniqueLogin(login);
    }
}
