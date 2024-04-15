package com.example.viacademy.mappers;

import com.example.viacademy.entities.Course;
import com.example.viacademy.web.dtos.requests.CreateCourseRequest;
import com.example.viacademy.web.dtos.responses.CreateCourseResponse;
import com.example.viacademy.web.dtos.responses.GetCourseResponse;
import com.example.viacademy.web.dtos.responses.UpdateCourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CreateCourseRequest request);

    CreateCourseResponse toCreateCourseResponse(Course course);

    @Mapping(source = "instructorProfile.id", target = "instructorProfileName")
    GetCourseResponse toGetCourseResponse(Course course);

    UpdateCourseResponse toUpdateCourseResponse(Course course);
}
