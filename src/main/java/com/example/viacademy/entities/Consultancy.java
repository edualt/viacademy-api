package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.ConsultancyCategory;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consultancies")
@Getter
@Setter
public class Consultancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDone;

    private Date dateOfConsultancy;

    @OneToMany(mappedBy = "consultancy", cascade = CascadeType.ALL)
    private List<ConsultancyCategory> consultancyCategories;

    @ManyToOne
    @JoinColumn(name = "instructor_profile_id")
    @JsonManagedReference
    private InstructorProfile instructorProfile;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}