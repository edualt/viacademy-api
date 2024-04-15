package com.example.viacademy.repositories;

import com.example.viacademy.entities.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseVideoRepository extends JpaRepository<CourseVideo, Long> {
}
