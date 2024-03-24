package com.example.viacademy.services.impls;

import com.example.viacademy.entities.User;
import com.example.viacademy.services.IAuthService;
import com.example.viacademy.services.IUserService;
import com.example.viacademy.types.JWTType;
import com.example.viacademy.utils.JWTUtils;
import com.example.viacademy.web.dtos.requests.AuthenticateRequest;
import com.example.viacademy.web.dtos.responses.AuthenticateResponse;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    private final JWTUtils jwtUtils;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(JWTUtils jwtUtils, IUserService userService, PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BaseResponse authenticate(AuthenticateRequest request) {
        Optional<User> optionalUser = userService.getByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
//            throw new AccessDeniedException();
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!isPasswordValid(request.getPassword(), user.getPassword())) {
//            throw new AccessDeniedException();
            throw new RuntimeException("Invalid password");
        }

        List<String> roles = user.getUserRoles().stream().map(userRole -> userRole.getRole().getName()).toList();
        Map<String, Object> payload = Map.of(
                "name", user.getFirstName() + " " + user.getLastName(),
                "roles", roles
        );
        String accessToken = jwtUtils.getToken(user.getEmail(), payload, JWTType.ACCESS_TOKEN);

        AuthenticateResponse response = new AuthenticateResponse();
        response.setAccessToken(accessToken);

        return BaseResponse.builder()
                .success(true)
                .message("Authenticated successfully")
                .data(response)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    private boolean isPasswordValid(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
