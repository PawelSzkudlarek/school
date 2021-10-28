package com.university.school.service;

import com.university.school.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public boolean isUniqueLogin(String login) {
        return !loginRepository.existsByValue(login);
    }

//    @PostConstruct
//    public void addSomeLogins(){
//        loginRepository.save(Login.of("Pablo"));
//        loginRepository.save(Login.of("Pablo1"));
//        loginRepository.save(Login.of("Pablo2"));
//        loginRepository.save(Login.of("Pablo3"));
//    }
}
