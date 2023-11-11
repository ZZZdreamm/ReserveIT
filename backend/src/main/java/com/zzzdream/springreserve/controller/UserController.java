package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.model.User;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.CurrentUser;
import com.zzzdream.springreserve.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
