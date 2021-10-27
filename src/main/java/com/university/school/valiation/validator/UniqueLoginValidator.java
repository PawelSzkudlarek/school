package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.school.model.dto.StudentDto;
import com.university.school.service.LoginService;
import com.university.school.valiation.annotation.UniqueLogin;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, StudentDto> {


    private final LoginService loginService;

    @Override
    public boolean isValid(StudentDto studentDto, ConstraintValidatorContext constraintValidatorContext) {
       log.info("validating login for new form");
        return false;
    }
}
