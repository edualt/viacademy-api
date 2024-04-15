package com.example.viacademy.web.dtos.requests;

import com.example.viacademy.entities.Consultancy;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class CreateScheduleConsultancyRequest {
    private Long consultancyId;

    private Date scheduleDate;

    private Boolean isDone;
}
