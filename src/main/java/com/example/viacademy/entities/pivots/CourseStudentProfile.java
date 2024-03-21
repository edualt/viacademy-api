package com.example.viacademy.entities.pivots;

import com.example.viacademy.entities.Course;
import com.example.viacademy.entities.StudentProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses_student_profiles")
@Getter
@Setter
public class CourseStudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

}
