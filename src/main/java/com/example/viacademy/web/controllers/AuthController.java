package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IAuthService;
import com.example.viacademy.web.dtos.requests.AuthenticateRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> authenticate(@RequestBody @Valid AuthenticateRequest request) {
        return authService.authenticate(request).apply();
    }
}
