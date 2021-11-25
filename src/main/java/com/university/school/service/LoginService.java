package com.university.school.service;

import com.university.school.model.entity.Login;
import com.university.school.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public boolean isLoginExists(String login) {
        return loginRepository.existsByValue(login);
    }

    public Set<String> createProposals(String login) {
        Set<String> proposals = new HashSet<>();
        final Set<Login> similarLogins = loginRepository.findByValueLike(login);

        while (proposals.size() < 3){
            String newLogin = login + RandomStringUtils.random(3, false, true);
            if(!similarLogins.contains(newLogin)){
                proposals.add(newLogin);
            }
        }
        return proposals;
    }
}
