package com.university.school.valiation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.school.exception.UniqueLoginException;
import com.university.school.repository.UserRepository;
import com.university.school.valiation.annotation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;
    private List<String> hints;

    @Override
    public void initialize(UniqueUsername login) {
        this.hints = Arrays.asList(login.hints());
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userRepository.existsByUsername(username)) {
            throw new UniqueLoginException("Provided login " + username + " exists!", createProposals(username));
        }
        return true;
    }

    private Set<String> createProposals(String username) {
        Set<String> proposals = new HashSet<>();

        final Set<String> similarUsernames = userRepository.findUsernamesLike(username);

        while (proposals.size() < 3) {
            String newLogin = username + RandomStringUtils.random(3, false, true);
            if (!similarUsernames.contains(newLogin)) {
                proposals.add(newLogin);
            }
        }
        return proposals;
    }

    private boolean isNotEmpty(String login) {
        return login != null && !login.trim().equals("");
    }
}
