package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IConsultancyService;
import com.example.viacademy.web.dtos.requests.CreateConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultancies")
public class ConsultancyController {

    private final IConsultancyService service;

    public ConsultancyController(IConsultancyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = service.get(id);
        return response.apply();
    }

    @GetMapping
    public ResponseEntity<BaseResponse> list() {
        BaseResponse response = service.list();
        return response.apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateConsultancyRequest request) {
        BaseResponse response = service.create(request);
        return response.apply();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateConsultancyRequest request) {
        BaseResponse response = service.update(id, request);
        return response.apply();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        BaseResponse response = service.delete(id);
        return response.apply();
    }
}
