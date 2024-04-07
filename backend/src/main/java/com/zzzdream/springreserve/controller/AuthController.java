package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.payload.LoginRequest;
import com.zzzdream.springreserve.payload.SignUpRequest;
import com.zzzdream.springreserve.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PatchMapping("/verify")
    public ModelAndView verifyUser(@RequestParam Long userId, @RequestParam String code) {
         ResponseEntity<?> result = authService.verifyUser(userId, code);
         if (result.getStatusCode().is2xxSuccessful()) {
             return new ModelAndView("redirect:/login");
         } else {
             return new ModelAndView("redirect:/error");
         }
    }
}
