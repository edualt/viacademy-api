package com.example.viacademy.web.controllers;

import com.example.viacademy.services.ICourseVideoService;
import com.example.viacademy.web.dtos.requests.CreateCourseVideoRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseVideoRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course-videos")
public class CourseVideoController {
    private final ICourseVideoService service;

    public CourseVideoController(ICourseVideoService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@RequestParam Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@RequestParam Long id, @Valid @RequestBody UpdateCourseVideoRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateCourseVideoRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
