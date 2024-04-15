package com.example.viacademy.services.impls;

import com.example.viacademy.entities.CourseVideo;
import com.example.viacademy.mappers.CourseVideoMapper;
import com.example.viacademy.repositories.ICourseVideoRepository;
import com.example.viacademy.services.ICourseVideoService;
import com.example.viacademy.web.dtos.requests.CreateCourseVideoRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseVideoRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class CourseVideoServiceImpl implements ICourseVideoService {
    private final ICourseVideoRepository repository;
    private final CourseVideoMapper mapper = CourseVideoMapper.INSTANCE;

    public CourseVideoServiceImpl(ICourseVideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseResponse get(Long id) {
        CourseVideo courseVideo = this.findOneAndEnsureExist(id);
        return BaseResponse.builder()
                .data(courseVideo)
                .message("Course video retrieved successfully")
                .success(true)
                .build();
    }

    @Override
    public BaseResponse create(CreateCourseVideoRequest request) {
        CourseVideo courseVideo = mapper.toCourseVideo(request);
        CourseVideo savedCourseVideo = repository.save(courseVideo);
        return BaseResponse.builder()
                .data(savedCourseVideo)
                .message("Course video created successfully")
                .success(true)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateCourseVideoRequest request) {
        final CourseVideo courseVideo = this.findOneAndEnsureExist(id);

        courseVideo.setTitle(request.getTitle());
        courseVideo.setDescription(request.getDescription());

        CourseVideo updatedCourseVideo = repository.save(courseVideo);
        return BaseResponse.builder()
                .data(updatedCourseVideo)
                .message("Course video updated successfully")
                .success(true)
                .build();
    }

    @Override
    public CourseVideo findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Course video not found"));
    }

}
