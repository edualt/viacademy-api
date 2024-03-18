package com.example.viacademy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "videos")
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    private Long duration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}