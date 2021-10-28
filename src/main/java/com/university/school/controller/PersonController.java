package com.university.school.controller;

import com.university.school.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/school/person")
@RequiredArgsConstructor
public class PersonController {

    private PersonService personService;
}
