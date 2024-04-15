package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseResponse {
    private Long id;
    private String name;
    private String description;
}
