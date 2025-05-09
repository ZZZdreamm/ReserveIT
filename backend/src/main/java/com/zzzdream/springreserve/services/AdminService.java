package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.model.auth.AuthProvider;
import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.RoleType;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.repository.*;
import com.zzzdream.springreserve.tests.ExampleDataLoader;
import com.zzzdream.springreserve.payload.ApiResponse;
import com.zzzdream.springreserve.setup.SetupDataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminService {
    private final SetupDataLoader setupDataLoader;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final UserService userService;
    private final ServiceSubjectService serviceService;
    private final AuthService authService;
    private  final PasswordEncoder passwordEncoder;
    private final ExampleDataLoader exampleDataLoader;
    @Value("${app.admin.email}")
    private String adminEmail;
    public AdminService(PrivilegeRepository privilegeRepository, RoleRepository roleRepository, ServiceRepository serviceRepository, UserRepository userRepository, SubjectRepository subjectRepository, UserService userService, ServiceSubjectService serviceService, AuthService authService, SetupDataLoader setupDataLoader, PasswordEncoder passwordEncoder, ExampleDataLoader exampleDataLoader) {
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.userService = userService;
        this.serviceService = serviceService;
        this.authService = authService;
        this.setupDataLoader = setupDataLoader;
        this.passwordEncoder = passwordEncoder;
        this.exampleDataLoader = exampleDataLoader;
    }
    public ResponseEntity<?> cleanDatabase() {
        deleteAllUsersExceptAdmin();
        subjectRepository.deleteAll();
        serviceRepository.deleteAll();
        return ResponseEntity.ok(new ApiResponse(true, "Database cleaned successfully"));
    }
    public ResponseEntity<?> populateDatabase(){
        exampleDataLoader.populateExampleData();
        return ResponseEntity.ok(new ApiResponse(true, "Database populated successfully"));
    }
    private void deleteAllUsersExceptAdmin() {
        User admin = userRepository.findByEmail(adminEmail).orElse(null);
        if (admin == null) {
            addAdmin();
        }
        Iterable<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user != admin) {
                userRepository.delete(user);
            }
        }
    }
    private void addAdmin(){
        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN);
        User user = new User();
        user.setName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Collections.singletonList(adminRole));
        user.setProvider(AuthProvider.local);
        userRepository.save(user);
    }

}
