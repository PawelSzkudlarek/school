package com.university.school.security.jwt;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.school.security.model.CustomPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.university.school.security.jwt.JwtFilter.AUTHORITIES;
import static com.university.school.security.jwt.JwtFilter.AUTHORITY;

@AllArgsConstructor
public class JwtVerifier extends OncePerRequestFilter {

    private final SecretKey secret;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        List<String> list = Collections.list(request.getHeaderNames());
        final String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (authorizationHeader.isBlank() || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            // maybe throw some exception
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            final Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);

            final var authorities = (List<Map<String, String>>) claimsJws.getBody().get(AUTHORITIES);

            final List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                    authorities.stream()
                            .map(a -> new SimpleGrantedAuthority(a.get(AUTHORITY)))
                            .collect(Collectors.toList());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(CustomPrincipal.builder()
                            .personId(claimsJws.getHeader().get(jwtConfig.getPersonIdHeader()).toString())
                            .username(claimsJws.getBody().getSubject())
                            .build(),
                            null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException ex) {
            throw new IllegalStateException(jwtConfig.getInvalidTokenMsg());
        }
        filterChain.doFilter(request, response);
    }
}
