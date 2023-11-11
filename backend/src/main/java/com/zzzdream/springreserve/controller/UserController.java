package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.model.User;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.CurrentUser;
import com.zzzdream.springreserve.security.UserPrincipal;
import com.zzzdream.springreserve.service.ServiceService;
import com.zzzdream.springreserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal);
    }
}
