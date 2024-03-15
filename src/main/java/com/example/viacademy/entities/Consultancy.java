package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.ConsultancyCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private Boolean isDone;

    private Date dateOfConsultancy;

    @OneToMany(mappedBy = "consultancy", cascade = CascadeType.ALL)
    private List<ConsultancyCategory> consultancyCategories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date createdAt;

    private Date updatedAt;

}