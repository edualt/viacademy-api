package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IUserService;
import com.example.viacademy.web.dtos.requests.CreateUserRequest;
import com.example.viacademy.web.dtos.requests.UpdateUserRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = service.get(id);
        return response.apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        BaseResponse response = service.create(request);
        return response.apply();
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        BaseResponse response = service.update(id, request);
        return response.apply();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        BaseResponse response = service.delete(id);
        return response.apply();
    }
}
