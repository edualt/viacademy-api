package com.example.viacademy.services.impl;

import com.example.viacademy.entities.User;
import com.example.viacademy.services.impls.AuthServiceImpl;
import com.example.viacademy.services.impls.UserServiceImpl;
import com.example.viacademy.types.JWTType;
import com.example.viacademy.utils.JWTUtils;
import com.example.viacademy.web.dtos.requests.AuthenticateRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private UserServiceImpl userService;
    @Mock
    private JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    public void init() {
        authService = new AuthServiceImpl(jwtUtils, userService, passwordEncoder);

        user = new User();
        user.setEmail("user@vicademy.com");
        user.setPassword("$2a$12$w3ll7PiusRJDipxH/rxgOOI99oewMvYrmyobh2vmMQym3hGM4mlCW"); // password: Test123@

    }

    @Test
    public void AuthService_CreateToken_ReturnsToken() {
        // Arrange
        when(userService.getByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Act
        AuthenticateRequest request = AuthenticateRequest
                .builder()
                .email(user.getEmail())
                .password("Test123@")
                .build();
        BaseResponse response = authService.authenticate(request);

        // Assert
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.CREATED);

    }

}
