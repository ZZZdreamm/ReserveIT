package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.model.services.Servicee;
import com.zzzdream.springreserve.model.services.Subject;
import com.zzzdream.springreserve.repository.ServiceRepository;
import com.zzzdream.springreserve.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ServiceSubjectService {
    private final ServiceRepository serviceRepository;
    private  final SubjectRepository subjectRepository;
    public ServiceSubjectService(ServiceRepository serviceRepository, SubjectRepository subjectRepository){
        this.serviceRepository = serviceRepository;
        this.subjectRepository = subjectRepository;
    }

    public ResponseEntity<?> attachToSubject(String serviceName, String subjectName){
        Servicee service = serviceRepository.findByName(serviceName).orElseThrow(() ->new ResourceNotFoundException("Service", "name", serviceName));
        Subject subject = subjectRepository.findByName(subjectName).orElseThrow(() ->new ResourceNotFoundException("Subject", "name", subjectName));
        if(subject.getServices().contains(service)){
            return ResponseEntity.badRequest().body(String.format("Service %s is already attached to subject %s", serviceName, subjectName));
        }
        subject.addServices(Collections.singletonList(service));
        subjectRepository.save(subject);
        return ResponseEntity.ok(String.format("Service %s has been attached to subject %s", serviceName, subjectName));
    }

}
