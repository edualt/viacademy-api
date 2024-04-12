package com.example.viacademy.web.controllers;

import com.example.viacademy.services.ICourseService;
import com.example.viacademy.web.dtos.requests.CreateCourseRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses")
public class CourseController {
    private final ICourseService service;

    public CourseController(ICourseService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    private ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    private ResponseEntity<BaseResponse> list() {
        BaseResponse baseResponse = service.list();

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    private ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateCourseRequest request) {
        BaseResponse baseResponse = service.create(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    private ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest request) {
        BaseResponse baseResponse = service.update(id, request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
