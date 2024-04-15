package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IScheduleConsultancyService;
import com.example.viacademy.web.dtos.requests.CreateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule-consultancy")
public class ScheduleConsultancyController {

    private final IScheduleConsultancyService service;

    public ScheduleConsultancyController(IScheduleConsultancyService scheduleConsultancyService) {
        this.service = scheduleConsultancyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse response = service.get(id);
        return response.apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateScheduleConsultancyRequest request){
        BaseResponse response = service.create(request);
        return response.apply();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id){
        BaseResponse response = service.delete(id);
        return response.apply();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateScheduleConsultancyRequest request){
        BaseResponse response = service.update(id, request);
        return response.apply();
    }

}
