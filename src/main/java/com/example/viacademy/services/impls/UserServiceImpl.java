package com.example.viacademy.services.impls;

import com.example.viacademy.entities.User;
import com.example.viacademy.repositories.IUserRepository;
import com.example.viacademy.services.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.example.viacademy.mappers.UserMapper;
import com.example.viacademy.web.dtos.requests.CreateUserRequest;
import com.example.viacademy.web.dtos.requests.UpdateUserRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository repository;
    private final UserMapper mapper;


    public UserServiceImpl(IUserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public BaseResponse get(Long id) {
        User user = this.findOneAndEnsureExist(id);

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toCreateUserResponse(user))
                .message("User retrieved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        if (repository.getByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        request.setPassword(encodePassword(request.getPassword()));

        User user = repository.save(mapper.INSTANCE.toUser(request));

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toCreateUserResponse(user))
                .message("User created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {
        User user = this.findOneAndEnsureExist(id);

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());

        User updatedUser = repository.save(user);

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toUpdateUserResponse(updatedUser))
                .message("User updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {
        User user = this.findOneAndEnsureExist(id);

        repository.delete(user);

        return BaseResponse.builder()
                .message("User deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public User findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

    @Override
    public User findOneAndEnsureExist(String email) {
        return repository.getByEmail(email).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
