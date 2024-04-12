package com.example.viacademy.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseVideoRequest {
    private String title;
    private String description;
    private String url;
    private Integer order;
}
