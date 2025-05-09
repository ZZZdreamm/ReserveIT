package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.exception.BadRequestException;
import com.zzzdream.springreserve.model.auth.AuthProvider;
import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.RoleType;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.mailMessages.MailMessage;
import com.zzzdream.springreserve.payload.ApiResponse;
import com.zzzdream.springreserve.payload.AuthResponse;
import com.zzzdream.springreserve.payload.LoginRequest;
import com.zzzdream.springreserve.payload.SignUpRequest;
import com.zzzdream.springreserve.repository.RoleRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.TokenProvider;
import com.zzzdream.springreserve.util.MailSender;
import com.zzzdream.springreserve.util.MessageUtils;
import com.zzzdream.springreserve.util.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RoleRepository roleRepository;
    private final MailSender mailSender;
    @Value("${app.baseUrl}")
    private String baseUrl;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RoleRepository roleRepository, MailSender mailSender) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.roleRepository = roleRepository;
        this.mailSender = mailSender;
    }

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }


    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER);
        String randomCode = RandomCodeGenerator.generateRandomCode(12);

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setProvider(AuthProvider.local);
        user.setVerificationCode(randomCode);
        user.setRoles(Collections.singletonList(userRole));
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        MailMessage mailMessage = MessageUtils.getMailMessage(result, randomCode);
        mailSender.sendMessage(mailMessage);

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    public ResponseEntity<?> verifyUser(Long userId, String code) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
        if (user.getVerificationCode().equals(code)) {
            user.setEmailVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return ResponseEntity.ok(new ApiResponse(true, "User verified successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Verification failed"));
    }
}
