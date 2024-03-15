package com.example.viacademy.entities.pivots;

import com.example.viacademy.entities.Category;
import com.example.viacademy.entities.Consultancy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consultancyCategories")
@Getter
@Setter
public class ConsultancyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultancy_id")
    @JsonBackReference
    private Consultancy consultancy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
}
