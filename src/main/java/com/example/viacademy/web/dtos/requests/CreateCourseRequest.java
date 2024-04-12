package com.example.viacademy.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseRequest {
    private String name;
    private String description;
    private Long instructorProfileId;
    private Long[] categoryIds;
}
