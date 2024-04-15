package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseVideoResponse {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Integer order;
    private String course;
}
