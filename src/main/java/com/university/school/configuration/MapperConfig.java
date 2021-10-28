package com.university.school.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Bean
    @Primary
    ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }
}
