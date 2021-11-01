package com.university.school.model.dto;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String field;
    private String message;

}
