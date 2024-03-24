package com.example.viacademy.security.services;

import com.example.viacademy.entities.User;
import com.example.viacademy.security.entities.UserDetailsImpl;
import com.example.viacademy.services.IUserRoleService;
import com.example.viacademy.services.IUserService;
import com.example.viacademy.web.dtos.responses.GetRoleResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserService userService;
    private final IUserRoleService userRoleService;

    public UserDetailsServiceImpl(IUserService userService, IUserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<GetRoleResponse> roles = userRoleService.getRolesByUserId(user.getId());

        return new UserDetailsImpl(user, getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<GetRoleResponse> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
