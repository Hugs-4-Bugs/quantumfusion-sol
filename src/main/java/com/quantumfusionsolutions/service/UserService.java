package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.JWT.CustomerUserDetailsService;
import com.quantumfusionsolutions.JWT.JwtFilter;
import com.quantumfusionsolutions.JWT.JwtUtil;
import com.quantumfusionsolutions.model.User;
import com.quantumfusionsolutions.repository.UserRepository;
import com.quantumfusionsolutions.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailUtil emailUtil;

    /*** ------------------- OTP SIGNUP FLOW ------------------- ***/

    public ResponseEntity<String> sendSignupOtp(String email) {
        try {
            if (userRepository.findByEmail(email).isPresent()) {
                return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
            }

            otpService.sendSignupOtp(email);
            return new ResponseEntity<>("OTP sent to email", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> verifyOtpAndSignup(String email, String otp, String fullName, String password, String role) {
        try {
            if (!otpService.verifyOtp(email, otp)) {
                return new ResponseEntity<>("Invalid or expired OTP", HttpStatus.BAD_REQUEST);
            }

            if (userRepository.findByEmail(email).isPresent()) {
                return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
            }

            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setHashedPassword(passwordEncoder.encode(password));
            user.setEmailVerified(true);
            user.setRole(role != null ? role : "USER"); // default role USER
            Timestamp now = new Timestamp(System.currentTimeMillis());
            user.setCreatedAt(now);
            user.setUpdatedAt(now);

            userRepository.save(user);
            return new ResponseEntity<>("Signup successful", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*** ------------------- LOGIN ------------------- ***/
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            String email = requestMap.get("email");
            String password = requestMap.get("password");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            User user = customerUserDetailsService.getUserDetail();
            // ✅ FIX: Use the actual user's role from the User object
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
            return new ResponseEntity<>("{\"token\":\"" + token + "\"}", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"message\":\"Bad Credentials\"}", HttpStatus.BAD_REQUEST);
        }
    }

    /*** ------------------- ADMIN METHODS ------------------- ***/
    public ResponseEntity<List<User>> getAllUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<String> updateUser(String userId, Map<String, String> requestMap) {
        if (!jwtFilter.isAdmin()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);

        User user = optionalUser.get();
        if (requestMap.containsKey("fullName")) user.setFullName(requestMap.get("fullName"));
        if (requestMap.containsKey("email")) user.setEmail(requestMap.get("email"));
        if (requestMap.containsKey("hashedPassword"))
            user.setHashedPassword(passwordEncoder.encode(requestMap.get("hashedPassword")));

        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String userId) {
        if (!jwtFilter.isAdmin()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);

        userRepository.deleteById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    /*** ------------------- PASSWORD MANAGEMENT ------------------- ***/
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User user = userRepository.findByEmail(jwtFilter.getCurrentUser())
                    .orElse(null); // unwrap Optional safely

            if (user == null) return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);

            String oldPassword = requestMap.get("oldPassword");
            String newPassword = requestMap.get("newPassword");

            if (!passwordEncoder.matches(oldPassword, user.getHashedPassword())) {
                return new ResponseEntity<>("Incorrect old password", HttpStatus.BAD_REQUEST);
            }

            user.setHashedPassword(passwordEncoder.encode(newPassword));
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);

            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            String email = requestMap.get("email");
            User user = userRepository.findByEmail(email)
                    .orElse(null); // unwrap Optional safely

            if (user == null) return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);

            // TODO: send email with temporary password or reset link
            return new ResponseEntity<>("Check your mail for credentials", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*** ------------------- TOKEN CHECK ------------------- ***/
    public ResponseEntity<String> checkToken() {
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
}