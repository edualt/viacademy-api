package com.example.viacademy.services;

import com.example.viacademy.entities.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getByEmail(String email);
}
