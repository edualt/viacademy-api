package com.example.viacademy.services;

import com.example.viacademy.entities.Course;
import com.example.viacademy.web.dtos.requests.CreateCourseRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;

public interface ICourseService {
    BaseResponse get(Long id);
    BaseResponse list();
    BaseResponse create(CreateCourseRequest request);
    BaseResponse update(Long id, UpdateCourseRequest request);
    Course findOneAndEnsureExist(Long id);
}
