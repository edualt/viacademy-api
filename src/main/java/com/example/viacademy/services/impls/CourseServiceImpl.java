package com.example.viacademy.services.impls;

import com.example.viacademy.entities.Course;
import com.example.viacademy.mappers.CourseMapper;
import com.example.viacademy.repositories.ICourseRepository;
import com.example.viacademy.services.ICourseService;
import com.example.viacademy.web.dtos.requests.CreateCourseRequest;
import com.example.viacademy.web.dtos.requests.UpdateCourseRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import com.example.viacademy.web.dtos.responses.GetCourseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseMapper courseMapper = CourseMapper.INSTANCE;
    private final ICourseRepository repository;

    public CourseServiceImpl(ICourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseResponse get(Long id) {
        Course course = findOneAndEnsureExist(id);
        GetCourseResponse response = courseMapper.toGetCourseResponse(course);

        return BaseResponse.builder()
                .data(response)
                .message("Course retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse list() {
        List<Course> courses = repository.findAll();
        List<GetCourseResponse> responses = courses.stream()
                .map(courseMapper::toGetCourseResponse)
                .toList();

        return BaseResponse.builder()
                .data(responses)
                .message("Courses retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateCourseRequest request) {
        Course course = courseMapper.toCourse(request);
        Course savedCourse = repository.save(course);
        GetCourseResponse response = courseMapper.toGetCourseResponse(savedCourse);

        return BaseResponse.builder()
                .data(response)
                .message("Course created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateCourseRequest request) {
        Course course = findOneAndEnsureExist(id);

        course.setName(request.getName());
        course.setDescription(request.getDescription());


        Course updatedCourse = repository.save(course);
        GetCourseResponse response = courseMapper.toGetCourseResponse(updatedCourse);

        return BaseResponse.builder()
                .data(response)
                .message("Course updated successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public Course findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
