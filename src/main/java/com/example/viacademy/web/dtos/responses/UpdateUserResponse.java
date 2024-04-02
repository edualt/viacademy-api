package com.example.viacademy.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UpdateUserResponse {
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}