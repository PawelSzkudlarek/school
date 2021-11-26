package com.university.school.service;

import com.university.school.annotations.Secured;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Secured
@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService(@Qualifier("Fake") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.contains("@")){
            return userDao.findUserByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        }
        return userDao.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
