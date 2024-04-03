package com.example.viacademy.services;

import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.web.dtos.requests.CreateConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;

import java.util.Optional;

public interface IConsultancyService {

    BaseResponse get(Long id);
    BaseResponse create(CreateConsultancyRequest request);
    BaseResponse update(Long id, UpdateConsultancyRequest request);
    BaseResponse delete(Long id);
    Consultancy findOneAndEnsureExist(Long id);

}
