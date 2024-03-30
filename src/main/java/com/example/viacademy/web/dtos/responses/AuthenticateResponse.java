package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateResponse {
    private String accessToken;
}