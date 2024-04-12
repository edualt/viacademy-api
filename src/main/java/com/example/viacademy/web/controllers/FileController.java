package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IFileService;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("files")
public class FileController {
    private final IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> uploadFile(MultipartFile file) {
        BaseResponse response = fileService.uploadFile(file);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
