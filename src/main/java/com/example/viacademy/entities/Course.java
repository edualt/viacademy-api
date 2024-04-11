package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.CourseCategory;
import com.example.viacademy.entities.pivots.CourseStudentProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseStudentProfile> studentProfileCourses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Video> videos;

    @ManyToOne
    @JoinColumn(name = "instructor_profile_id")
    @JsonManagedReference
    private InstructorProfile instructorProfile;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}