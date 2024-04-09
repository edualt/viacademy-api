package com.example.viacademy.utils;

import com.example.viacademy.entities.User;
import com.example.viacademy.security.entities.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {
    public User getUserAuthenticated(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getUser();
    }
}