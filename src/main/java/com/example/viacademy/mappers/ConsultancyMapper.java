package com.example.viacademy.mappers;

import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.web.dtos.requests.CreateConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateConsultancyRequest;
import com.example.viacademy.web.dtos.responses.CreateConsultancyResponse;
import com.example.viacademy.web.dtos.responses.GetConsultancyResponse;
import com.example.viacademy.web.dtos.responses.UpdateConsultancyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConsultancyMapper {
    ConsultancyMapper INSTANCE = Mappers.getMapper(ConsultancyMapper.class);

    Consultancy toConsultancy(CreateConsultancyRequest request);

    CreateConsultancyResponse toCreateConsultancyResponse(Consultancy consultancy);

    GetConsultancyResponse toGetConsultancyResponse(Consultancy consultancy);

    UpdateConsultancyResponse toUpdateConsultancyResponse(Consultancy consultancy);
}
