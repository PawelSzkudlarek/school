package com.university.school.service;

import com.university.school.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    private boolean isUniqueLogin(String login){
        return loginRepository.existsByValue(login);
    }
}
