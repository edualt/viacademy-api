package com.example.viacademy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    private Date dateOfBirth;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private InstructorProfile instructorProfile;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}