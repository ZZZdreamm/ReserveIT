package com.zzzdream.springreserve.service;

import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.model.User;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.CurrentUser;
import com.zzzdream.springreserve.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
