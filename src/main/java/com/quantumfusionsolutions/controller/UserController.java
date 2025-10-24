package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.model.User;
import com.quantumfusionsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        return userService.login(requestMap);
    }

    // Get all users (Admin only)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update user (Admin only)
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody Map<String, String> requestMap) {
        return userService.updateUser(userId, requestMap);
    }


    // Delete user (Admin only)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    // Change password
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap) {
        return userService.changePassword(requestMap);
    }

    // Forgot password
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap) {
        return userService.forgotPassword(requestMap);
    }

    // Check token
    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken() {
        return userService.checkToken();
    }
}

