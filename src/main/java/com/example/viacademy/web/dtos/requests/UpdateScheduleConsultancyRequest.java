package com.example.viacademy.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class UpdateScheduleConsultancyRequest {
    private Long consultancyId;

    private Date scheduleDate;

    private Boolean isDone;
}
