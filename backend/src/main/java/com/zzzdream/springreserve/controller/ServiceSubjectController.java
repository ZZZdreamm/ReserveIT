package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.services.ServiceSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceSubject")
@PreAuthorize("hasAuthority('READ_PRIVILEGE')")
public class ServiceSubjectController {
    private final ServiceSubjectService serviceSubjectService;
    public ServiceSubjectController(ServiceSubjectService serviceSubjectService){
        this.serviceSubjectService = serviceSubjectService;
    }
    @PatchMapping("/attachToSubject")
    public ResponseEntity<?> attachToSubject(@RequestParam String serviceName, @RequestParam String subjectName){
        return serviceSubjectService.attachToSubject(serviceName, subjectName);
    }
}
