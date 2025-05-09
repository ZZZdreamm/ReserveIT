package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.RoleType;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.payload.ApiResponse;
import com.zzzdream.springreserve.repository.RoleRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.CurrentUser;
import com.zzzdream.springreserve.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return user;
    }
    public ResponseEntity<?> deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().body(new ApiResponse(true, "User deleted successfully"));
    }

    public ResponseEntity<?> makeUserStaffMember(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Role staffRole = roleRepository.findByRoleType(RoleType.ROLE_STAFF);
        user.getRoles().add(staffRole);
        userRepository.save(user);
        return ResponseEntity.ok().body(new ApiResponse(true, "User is now an admin"));
    }
}
