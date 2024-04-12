package com.example.viacademy.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseVideoRequest {
    private String title;
    private String description;
    private String url;
    private Integer order;
    private Long courseId;
}
