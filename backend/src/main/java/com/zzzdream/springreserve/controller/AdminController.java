package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @DeleteMapping("/cleanDatabase")
    public ResponseEntity<?> cleanDatabase() {
        return adminService.cleanDatabase();
    }
    @PostMapping("/populateDatabase")
    public ResponseEntity<?>  populateDatabase() {
        return adminService.populateDatabase();
    }
}
