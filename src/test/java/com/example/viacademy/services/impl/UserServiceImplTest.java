package com.example.viacademy.services.impl;

import com.example.viacademy.entities.User;
import com.example.viacademy.mappers.UserMapper;
import com.example.viacademy.repositories.IUserRepository;
import com.example.viacademy.services.impls.UserServiceImpl;
import com.example.viacademy.web.dtos.requests.CreateUserRequest;
import com.example.viacademy.web.dtos.requests.UpdateUserRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private IUserRepository userRepository;

    private UserMapper userMapper;

    private User user;

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl(userRepository, userMapper);

        user = new User();
        user.setId(89L);
        user.setFirstName("Test");
    }

    @Test
    public void whenGivenId_shouldReturnUser_ifFound() {
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        BaseResponse response = userService.get(user.getId());

        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.OK);
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist_get() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        assertThrows(RuntimeException.class, () -> userService.get(user.getId()));
    }

    @Test
    public void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Test");
        request.setLastName("User");
        request.setEmail("usuario@prueba.com");
        request.setPassword("Test123@");
        request.setDateOfBirth(new Date());

        when(userService.getByEmail(request.getEmail())).thenReturn(Optional.empty());

        BaseResponse response = userService.create(request);

        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.CREATED);
    }

//    TODO: Implement "user already exists" exception
//    @Test
//    public void testCrearUsuarioExistente() {
//        // Arrange
//        CreateUserRequest request = new CreateUserRequest();
//        request.setFirstName("Test");
//        request.setLastName("User");
//        request.setEmail("usuario@prueba.com"); // Este correo ya está registrado
//        request.setPassword("Test123@");
//        request.setDateOfBirth(new Date());
//
//        // Simulamos el comportamiento de UserService.getByEmail para que retorne un Optional con un usuario existente
//        User existingUser = new User();
//        existingUser.setId(1L);
//        existingUser.setEmail(request.getEmail());
//        when(userService.getByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));
//
//        // Act
//        BaseResponse response = userService.create(request);
//
//        // Assert
//        Assertions.assertFalse(response.getSuccess());
//        Assertions.assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
//    }

    @Test
    public void whenGivenId_shouldUpdateUser_ifFound() {
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        UpdateUserRequest request = new UpdateUserRequest();
        request.setFirstName("Updated");

        BaseResponse response = userService.update(user.getId(), request);

        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.OK);
    }

//  TODO: Implement "User doesnt exist" exception
    @Test
    public void should_throw_exception_when_user_doesnt_exist() {
        UpdateUserRequest request = new UpdateUserRequest();

        user.setFirstName("Updated");

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(RuntimeException.class, () -> userService.update(user.getId(), request));
    }

    @Test
    public void should_delete_user() {
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        BaseResponse response = userService.delete(user.getId());

        verify(userRepository).delete(user);
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.NO_CONTENT);
    }

//  TODO: Implement "User doesnt exist" exception
    @Test
    public void should_throw_exception_when_user_doesnt_exist_delete() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        assertThrows(RuntimeException.class, () -> userService.delete(user.getId()));
    }
}
