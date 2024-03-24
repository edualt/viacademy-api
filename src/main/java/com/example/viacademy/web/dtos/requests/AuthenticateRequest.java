package com.example.viacademy.web.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticateRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
