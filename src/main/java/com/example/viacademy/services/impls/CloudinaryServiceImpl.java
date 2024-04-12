package com.example.viacademy.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.viacademy.services.IFileService;
import com.example.viacademy.utils.FileUtils;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements IFileService {

    @Value("${cloudinary.url}")
    private String CLOUDINARY_URL;

    private Cloudinary cloudinary;

    private final FileUtils fileUtils;

    public CloudinaryServiceImpl(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    @Override
    public BaseResponse uploadFile(MultipartFile multipartFile) {
        Map options = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true
        );

        try {
            File file = fileUtils.toFile(multipartFile);
            Map response = cloudinary.uploader().upload(file, options);
            file.delete();

            String url = (String) response.get("url");

            Map<String, String> data = Map.of("image_url", url);

            return BaseResponse.builder()
                    .data(data)
                    .message("File uploaded successfully")
                    .success(true)
                    .httpStatus(HttpStatus.CREATED)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Error converting multipart file to file");
        }

    }


    @PostConstruct
    public void init() {
        cloudinary = new Cloudinary(CLOUDINARY_URL);
        cloudinary.config.secure = true;
    }
}
