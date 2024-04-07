package com.example.viacademy.web.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateConsultancyResponse {
    private String title;
    private String description;
    private Long[] categoryIds;
}
