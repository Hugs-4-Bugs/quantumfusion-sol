package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.dto.LoginRequest;
import com.quantumfusionsolutions.dto.SignupRequest;
import com.quantumfusionsolutions.model.User;
import com.quantumfusionsolutions.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest.getFullName(), signupRequest.getEmail(), signupRequest.getPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
