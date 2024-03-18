package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.ConsultancyCategory;
import com.example.viacademy.entities.pivots.CourseCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String name;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ConsultancyCategory> consultancyCategories;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}