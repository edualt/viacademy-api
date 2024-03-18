package com.example.viacademy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "instructor_profiles")
@Getter
@Setter
public class InstructorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "instructorProfile")
    private List<Course> courses;

    @OneToMany(mappedBy = "instructorProfile")
    private List<Consultancy> consultancies;
}
