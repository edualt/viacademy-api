package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateConsultancyResponse {
    private String title;
    private String description;
    private Boolean isDone;
    private Long[] categoryIds;
    private Date dateOfConsultancy;
}
