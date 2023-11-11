package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.repository.ServiceRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/me")
    public String getCurrentService() {
        return "service";
    }
}
