package com.zzzdream.springreserve.mappers;

import com.zzzdream.springreserve.model.services.Subject;
import com.zzzdream.springreserve.model.services.SubjectCreateDto;
import com.zzzdream.springreserve.model.services.SubjectGetDto;
import com.zzzdream.springreserve.model.services.SubjectGetDtoWithServices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ServiceMapper.class}) // ServiceMapperImpl works
public interface SubjectMapper {
    SubjectGetDto mapToGetDto(Subject subject);
    Subject mapToSubject(SubjectCreateDto subjectCreateDto);
    SubjectGetDtoWithServices mapToGetDtoWithServices(Subject subject);
    Subject mapToSubject(SubjectGetDtoWithServices subjectGetDto);
}
