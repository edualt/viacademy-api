package com.example.viacademy.services;

import com.example.viacademy.web.dtos.responses.GetRoleResponse;

import java.util.List;

public interface IUserRoleService {
    List<GetRoleResponse> getRolesByUserId(Long userId);
}
