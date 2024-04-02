package com.example.viacademy.services;

import com.example.viacademy.entities.User;

import java.util.Optional;

import com.example.viacademy.web.dtos.requests.CreateUserRequest;
import com.example.viacademy.web.dtos.requests.UpdateUserRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;

public interface IUserService {
    BaseResponse get(Long id);
    BaseResponse create(CreateUserRequest request);
    BaseResponse update(Long id, UpdateUserRequest request);
    BaseResponse delete(Long id);
    User findOneAndEnsureExist(Long id);
    User findOneAndEnsureExist(String email);
    Optional<User> getByEmail(String email);

}
