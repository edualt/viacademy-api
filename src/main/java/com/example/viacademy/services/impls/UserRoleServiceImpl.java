package com.example.viacademy.services.impls;

import com.example.viacademy.entities.projections.RoleProjection;
import com.example.viacademy.repositories.IUserRoleRepository;
import com.example.viacademy.services.IUserRoleService;
import com.example.viacademy.web.dtos.responses.GetRoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    private final IUserRoleRepository repository;

    public UserRoleServiceImpl(IUserRoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<GetRoleResponse> getRolesByUserId(Long userId) {
        List<RoleProjection> roles = repository.findRolesByUserId(userId);

        return roles.stream().map(this::toRoleResponse).collect(Collectors.toList());
    }

    GetRoleResponse toRoleResponse(RoleProjection roleProjection) {
        GetRoleResponse getRoleResponse = new GetRoleResponse();

        getRoleResponse.setId(roleProjection.getId());
        getRoleResponse.setName(roleProjection.getName());

        return getRoleResponse;
    }
}
