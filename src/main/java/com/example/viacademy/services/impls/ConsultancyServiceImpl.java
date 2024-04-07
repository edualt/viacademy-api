package com.example.viacademy.services.impls;


import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.entities.User;
import com.example.viacademy.mappers.ConsultancyMapper;
import com.example.viacademy.repositories.IConsultancyRepository;
import com.example.viacademy.services.IConsultancyService;
import com.example.viacademy.utils.AuthenticationUtils;
import com.example.viacademy.web.dtos.requests.CreateConsultancyRequest;
import com.example.viacademy.web.dtos.requests.UpdateConsultancyRequest;
import com.example.viacademy.web.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ConsultancyServiceImpl implements IConsultancyService {
    private final IConsultancyRepository repository;

    private final ConsultancyMapper mapper;

    private final AuthenticationUtils authenticationUtils;

    public ConsultancyServiceImpl(IConsultancyRepository repository, ConsultancyMapper mapper, AuthenticationUtils authenticationUtils) {
        this.repository = repository;
        this.mapper = mapper;
        this.authenticationUtils = authenticationUtils;
    }

    @Override
    public BaseResponse get(Long id) {
        Consultancy consultancy = findOneAndEnsureExist(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authenticationUtils.getUserAuthenticated(authentication);

        if(!consultancy.getInstructorProfile().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to access this consultancy");
        }

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toGetConsultancyResponse(consultancy))
                .message("Consultancy retrieved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateConsultancyRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authenticationUtils.getUserAuthenticated(authentication);

        Consultancy consultancy = mapper.INSTANCE.toConsultancy(request);

        consultancy.setInstructorProfile(user.getInstructorProfile());

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toCreateConsultancyResponse(repository.save(consultancy)))
                .message("Consultancy created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateConsultancyRequest request) {
        Consultancy consultancy = findOneAndEnsureExist(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authenticationUtils.getUserAuthenticated(authentication);

        if(!consultancy.getInstructorProfile().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this consultancy");
        }

        consultancy.setTitle(request.getTitle());
        consultancy.setDescription(request.getDescription());
//        consultancy.setConsultancyCategories(request.getCategoryIds());

        return BaseResponse.builder()
                .data(mapper.INSTANCE.toUpdateConsultancyResponse(repository.save(consultancy)))
                .message("Consultancy updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {
        Consultancy consultancy = findOneAndEnsureExist(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authenticationUtils.getUserAuthenticated(authentication);

        if(!consultancy.getInstructorProfile().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this consultancy");
        }

        repository.delete(consultancy);

        return BaseResponse.builder()
                .message("Consultancy deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    @Override
    public Consultancy findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }
}
