package com.example.viacademy.services;

import com.example.viacademy.entities.CourseVideo;
import com.example.viacademy.web.dtos.requests.CreateCourseVideoRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseVideoRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import com.example.viacademy.web.dtos.responses.CreateCourseVideoResponse;

public interface ICourseVideoService {
    BaseResponse get(Long id);
    BaseResponse create(CreateCourseVideoRequest request);
    BaseResponse update(Long id, UpdateCourseVideoRequest request);
    CourseVideo findOneAndEnsureExist(Long id);
}
