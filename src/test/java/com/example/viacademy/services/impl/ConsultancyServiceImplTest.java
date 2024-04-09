package com.example.viacademy.services.impl;

import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.entities.InstructorProfile;
import com.example.viacademy.entities.User;
import com.example.viacademy.mappers.ConsultancyMapper;
import com.example.viacademy.repositories.IConsultancyRepository;
import com.example.viacademy.security.entities.UserDetailsImpl;
import com.example.viacademy.services.impls.ConsultancyServiceImpl;
import com.example.viacademy.utils.AuthenticationUtils;
import com.example.viacademy.web.dtos.requests.CreateConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultancyServiceImplTest {

    @Mock
    private ConsultancyServiceImpl consultancyService;

    @Mock
    private IConsultancyRepository consultancyRepository;

    private ConsultancyMapper consultancyMapper;

    private Consultancy consultancy;

    private AuthenticationUtils authenticationUtils;

    private User user;

    @BeforeEach
    public void init() {
        authenticationUtils = new AuthenticationUtils();
        consultancyService = new ConsultancyServiceImpl(consultancyRepository, consultancyMapper, authenticationUtils);

        user = new User();
        user.setId(1L);
        user.setEmail("test@mail.com");

        InstructorProfile instructorProfile = new InstructorProfile();
        instructorProfile.setId(1L);
        instructorProfile.setUser(user);

        user.setInstructorProfile(instructorProfile);

        consultancy = new Consultancy();
        consultancy.setId(89L);
        consultancy.setTitle("Test");
        consultancy.setInstructorProfile(instructorProfile);
    }

    @Test
    public void whenGivenId_shouldReturnConsultancy_ifFound(){
        // Arrange
        given(consultancyRepository.findById(89L)).willReturn(Optional.of(consultancy));

        // Act
        BaseResponse response = consultancyService.get(89L);

        // Assert
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.OK);
    }

    @Test
    public void whenSavingConsultancy_shouldReturnConsultancy_IfIsAuthenticated() {
        CreateConsultancyRequest request = new CreateConsultancyRequest();
        request.setTitle("Test");

        Authentication authentication = getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(consultancyRepository.save(any(Consultancy.class))).thenReturn(consultancy);

        // Act
        BaseResponse response = consultancyService.create(request);

        // Assert
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.CREATED);
    }

    @Test
    public void whenUpdatingConsultancy_shouldReturnConsultancy_ifUserIsOwner() {
        // Arrange
        UpdateConsultancyRequest request = new UpdateConsultancyRequest();
        request.setTitle("Test");

        Authentication authentication = getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        given(consultancyRepository.findById(89L)).willReturn(Optional.of(consultancy));

        // Act
        BaseResponse response = consultancyService.update(89L, request);

        // Assert
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.OK);
    }

    @Test
    public void whenDeletingConsultancy_shouldReturnConsultancy_ifUserIsOwner() {
        // Arrange
        Authentication authentication = getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        given(consultancyRepository.findById(89L)).willReturn(Optional.of(consultancy));

        // Act
        BaseResponse response = consultancyService.delete(89L);

        // Assert
        Assertions.assertTrue(response.getSuccess());
        Assertions.assertSame(response.getHttpStatus(), HttpStatus.OK);
    }

    @Test
    public void whenDeletingConsultancy_shouldThrowException_ifUserIsNotOwner() {
        // Arrange
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test@mail.com");

        InstructorProfile instructorProfile2 = new InstructorProfile();
        instructorProfile2.setId(2L);
        instructorProfile2.setUser(user2);

        user2.setInstructorProfile(instructorProfile2);

        Consultancy consultancy2 = new Consultancy();
        consultancy2.setId(89L);
        consultancy2.setTitle("Test");
        consultancy2.setInstructorProfile(instructorProfile2);

        Authentication authentication = getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        given(consultancyRepository.findById(89L)).willReturn(Optional.of(consultancy2));

        // Act
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> consultancyService.delete(89L));

        // Assert
        assertEquals("You are not allowed to delete this consultancy", exception.getMessage());
    }

    private static Authentication getAuthentication(User user) {
        UserDetails userDetails = new UserDetailsImpl(user, Collections.emptyList()); // Esto es un ejemplo, asegúrate de configurar correctamente UserDetailsImpl con los datos necesarios

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "test");
        return authentication;
    }
}
