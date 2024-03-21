package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.CourseStudentProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "studentProfile")
    private List<CourseStudentProfile> courseStudentProfiles;

}
