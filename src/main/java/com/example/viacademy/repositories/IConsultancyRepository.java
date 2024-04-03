package com.example.viacademy.repositories;

import com.example.viacademy.entities.Consultancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultancyRepository extends JpaRepository<Consultancy, Long> {
}
