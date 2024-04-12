package com.example.viacademy.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {
    private String name;
    private String description;
    private Long[] categoryIds;
}
