package com.example.viacademy.services.impls;

import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.entities.ScheduleConsultancy;
import com.example.viacademy.repositories.IScheduleConsultancyRepository;
import com.example.viacademy.services.IConsultancyService;
import com.example.viacademy.services.IScheduleConsultancyService;
import com.example.viacademy.web.dtos.requests.CreateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateScheduleConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConsultancyImpl implements IScheduleConsultancyService {

    private final IScheduleConsultancyRepository repository;

    private final IConsultancyService consultancyService;

    public ScheduleConsultancyImpl(IScheduleConsultancyRepository scheduleConsultancyRepository, IConsultancyService consultancyService) {
        this.repository = scheduleConsultancyRepository;
        this.consultancyService = consultancyService;
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(repository.findById(id))
                .message("Schedule Consultancy retrieved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateScheduleConsultancyRequest request) {

        Consultancy consultancy = consultancyService.findOneAndEnsureExist(request.getConsultancyId());

        if (repository.existsByScheduleDate(request.getScheduleDate())) {
            return BaseResponse.builder()
                    .message("Consultancy already scheduled for this date")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }

        ScheduleConsultancy scheduleConsultancy = from(request, consultancy);

        return BaseResponse.builder()
                .data(repository.save(scheduleConsultancy))
                .message("Consultancy scheduled")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.deleteById(id);
        return BaseResponse.builder()
                .message("Schedule Consultancy deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateScheduleConsultancyRequest request) {
        ScheduleConsultancy scheduleConsultancy = repository.findById(id).orElse(null);

        if (scheduleConsultancy == null) {
            return BaseResponse.builder()
                    .message("Schedule Consultancy not found")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }

        scheduleConsultancy.setIsDone(request.getIsDone());

        return BaseResponse.builder()
                .data(repository.save(scheduleConsultancy))
                .message("Schedule Consultancy updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private ScheduleConsultancy from(CreateScheduleConsultancyRequest request, Consultancy consultancy){
        ScheduleConsultancy scheduleConsultancy = new ScheduleConsultancy();
        scheduleConsultancy.setConsultancy(consultancy);
        scheduleConsultancy.setScheduleDate(request.getScheduleDate());
        scheduleConsultancy.setIsDone(request.getIsDone());
        return scheduleConsultancy;
    }
}
