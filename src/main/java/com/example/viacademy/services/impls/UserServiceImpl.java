package com.example.viacademy.services.impls;

import com.example.viacademy.entities.User;
import com.example.viacademy.repositories.IUserRepository;
import com.example.viacademy.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository repository;

    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return repository.getByEmail(email);
    }
}
