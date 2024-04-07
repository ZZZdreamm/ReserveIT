package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.CurrentUser;
import com.zzzdream.springreserve.security.UserPrincipal;
import com.zzzdream.springreserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public ResponseEntity<?> deleteUser(@Param("id") Long userId) {
        return userService.deleteUser(userId);
    }

    @PatchMapping("/makeStaff")
    @PreAuthorize("hasRole('STAFF')")
    @ResponseBody
    public ResponseEntity<?> makeUserStaffMember(@RequestParam(name = "id") Long userId) {
        return userService.makeUserStaffMember(userId);
    }
}
