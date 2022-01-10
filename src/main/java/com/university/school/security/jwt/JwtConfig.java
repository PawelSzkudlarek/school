package com.university.school.security.jwt;

import com.university.school.annotations.Secured;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Secured
@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String tokenPrefix;
    private String authorizationHeader;
    private String invalidTokenMsg;
    private Integer tokenExpirationAfterDays;
    private String personIdHeader;
    private String secret;

}
