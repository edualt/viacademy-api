package com.example.viacademy.services;

import com.example.viacademy.web.dtos.requests.CreateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;

public interface IScheduleConsultancyService {
    BaseResponse get(Long id);
    BaseResponse create(CreateScheduleConsultancyRequest request);
    BaseResponse delete(Long id);
    BaseResponse update(Long id, UpdateScheduleConsultancyRequest request);
}
