package com.example.viacademy.web.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateConsultancyRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private Long[] categoryIds;

}
