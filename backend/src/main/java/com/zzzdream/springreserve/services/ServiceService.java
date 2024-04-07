package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.mappers.ServiceMapper;
import com.zzzdream.springreserve.model.services.ServiceCreateDto;
import com.zzzdream.springreserve.model.services.ServiceGetDtoWithSubjects;
import com.zzzdream.springreserve.model.services.ServiceGetDto;
import com.zzzdream.springreserve.model.services.Servicee;
import com.zzzdream.springreserve.repository.ServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper){
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }
    public List<? extends ServiceGetDto> getAllServices(boolean withSubjects){
        List<Servicee> services = serviceRepository.findAll();
        if(withSubjects){
            return services.stream().map(serviceMapper::mapToGetDtoWithSubjects).collect(Collectors.toList());
        }else{
            return services.stream().map(serviceMapper::mapToGetDto).collect(Collectors.toList());
        }
    }

    public ResponseEntity<?> createService(ServiceCreateDto serviceCreateDto){
        if(serviceRepository.existsByName(serviceCreateDto.name)){
            return ResponseEntity.badRequest().body("Service with this name already exists");
        }
        Servicee service = new Servicee(serviceCreateDto);
        serviceRepository.save(service);
        return ResponseEntity.ok("Service have been created successfully");
    }
}
