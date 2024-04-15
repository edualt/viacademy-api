package com.example.viacademy.mappers;

import com.example.viacademy.entities.CourseVideo;
import com.example.viacademy.web.dtos.requests.CreateCourseVideoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseVideoMapper {
    CourseVideoMapper INSTANCE = Mappers.getMapper(CourseVideoMapper.class);

    CourseVideo toCourseVideo(CreateCourseVideoRequest request);

}
