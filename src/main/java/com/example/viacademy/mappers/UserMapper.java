package com.example.viacademy.mappers;

import com.example.viacademy.entities.User;
import com.example.viacademy.web.dtos.requests.CreateUserRequest;
import com.example.viacademy.web.dtos.responses.CreateUserResponse;
import com.example.viacademy.web.dtos.responses.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    CreateUserResponse toCreateUserResponse(User user);

    User toUser(CreateUserRequest request);
    UpdateUserResponse toUpdateUserResponse(User user);
}
