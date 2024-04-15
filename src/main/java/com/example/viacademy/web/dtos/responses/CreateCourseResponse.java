package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCourseResponse {
    private Long id;
    private String name;
    private String description;
}
