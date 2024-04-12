package com.example.viacademy.security;

import com.example.viacademy.security.entrypoints.ExceptionAccessDeniedHandlerImpl;
import com.example.viacademy.security.entrypoints.ExceptionAuthenticationEntryPointImpl;
import com.example.viacademy.security.filters.JWTVerifierFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTVerifierFilter jwtVerifierFilter;
    private final ExceptionAccessDeniedHandlerImpl accessDeniedHandler;
    private final ExceptionAuthenticationEntryPointImpl exceptionAuthenticationEntryPoint;

    public SecurityConfig(JWTVerifierFilter jwtVerifierFilter, ExceptionAccessDeniedHandlerImpl accessDeniedHandler, ExceptionAuthenticationEntryPointImpl exceptionAuthenticationEntryPoint) {
        this.jwtVerifierFilter = jwtVerifierFilter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.exceptionAuthenticationEntryPoint = exceptionAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(exceptionAuthenticationEntryPoint);
                    exception.accessDeniedHandler(accessDeniedHandler);
                })
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/files/**").permitAll()
                        .requestMatchers("/roles/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}