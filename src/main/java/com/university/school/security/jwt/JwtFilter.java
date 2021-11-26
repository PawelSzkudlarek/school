package com.university.school.security.jwt;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.school.model.entity.Person;
import com.university.school.model.entity.User;
import com.university.school.security.model.UsernameAndPasswordAuthenticationRequest;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class JwtFilter extends UsernamePasswordAuthenticationFilter {

    public static final String AUTHORITIES = "authorities";
    public static final String AUTHORITY = "authority";
    private final AuthenticationManager authenticationManager;
    private final SecretKey secret;
    private final JwtConfig jwtConfig;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        final UsernameAndPasswordAuthenticationRequest authenticationRequest = new
                ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

        final Authentication authenticate = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return authenticationManager.authenticate(authenticate);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        final String personId = Optional.of((User) authResult.getPrincipal())
                .map(User::getPerson).map(Person::getId).map(String::valueOf).orElse("");
        final String token = Jwts.builder()
                .setId(personId)
                .setSubject(authResult.getName())
                .setHeader(Map.of(jwtConfig.getPersonIdHeader(), personId))
                .claim(AUTHORITIES, authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secret)
                .compact();

        response.setHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
}
