package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.User;
import com.quantumfusionsolutions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(String fullName, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        // Set other fields and save
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }
}
