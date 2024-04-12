package com.example.viacademy.services;

import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    BaseResponse uploadFile(MultipartFile file);
}
