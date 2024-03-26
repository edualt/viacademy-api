package com.example.viacademy.services;

import com.example.viacademy.web.dtos.requests.AuthenticateRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;

public interface IAuthService {
    BaseResponse authenticate(AuthenticateRequest request);
}
