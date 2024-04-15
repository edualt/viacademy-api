package com.example.viacademy.repositories;

import com.example.viacademy.entities.ScheduleConsultancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IScheduleConsultancyRepository extends JpaRepository<ScheduleConsultancy, Long> {

    Boolean existsByScheduleDate(Date scheduleDate);

}
