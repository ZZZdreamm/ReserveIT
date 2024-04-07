package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.model.services.ServiceCreateDto;
import com.zzzdream.springreserve.model.services.ServiceGetDtoWithSubjects;
import com.zzzdream.springreserve.model.services.ServiceGetDto;
import com.zzzdream.springreserve.services.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service")
@PreAuthorize("hasAuthority('READ_PRIVILEGE')")
public class ServiceController {
    private final ServiceService serviceService;
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    @GetMapping("/me")
    public String getCurrentService() {
        return "Service is incoming";
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceCreateDto serviceCreateDto) {
        return serviceService.createService(serviceCreateDto);
    }
    @GetMapping("/all")
    public @ResponseBody List<? extends ServiceGetDto> getAllServices(@RequestParam(required = false) boolean withSubjects){
        return serviceService.getAllServices(withSubjects);
    }
}
