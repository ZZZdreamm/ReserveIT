package com.zzzdream.springreserve.mappers;

import com.zzzdream.springreserve.model.services.ServiceGetDto;
import com.zzzdream.springreserve.model.services.ServiceGetDtoWithSubjects;
import com.zzzdream.springreserve.model.services.Servicee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectMapperImpl.class})
public interface ServiceMapper {
    ServiceGetDto mapToGetDto(Servicee service);
    @Mapping(target = "subjects", source = "subjects")
    ServiceGetDtoWithSubjects mapToGetDtoWithSubjects(Servicee service);
    Servicee mapToService(ServiceGetDto serviceGetDto);
    Servicee mapToService(ServiceGetDtoWithSubjects serviceGetDtoWithSubjects);

}
