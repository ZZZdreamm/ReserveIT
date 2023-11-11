package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.exception.BadRequestException;
import com.zzzdream.springreserve.model.AuthProvider;
import com.zzzdream.springreserve.model.User;
import com.zzzdream.springreserve.payload.ApiResponse;
import com.zzzdream.springreserve.payload.AuthResponse;
import com.zzzdream.springreserve.payload.LoginRequest;
import com.zzzdream.springreserve.payload.SignUpRequest;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.TokenProvider;
import com.zzzdream.springreserve.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}
