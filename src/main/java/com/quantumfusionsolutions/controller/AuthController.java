package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Step 1: Send OTP
    @PostMapping("/signup/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return userService.sendSignupOtp(email);
    }

    // Step 2: Verify OTP and signup
    @PostMapping("/signup/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String fullName = request.get("fullName");
        String password = request.get("password");
        String role = request.get("role"); // get role from request
        return userService.verifyOtpAndSignup(email, otp, fullName, password, role);
    }
}
