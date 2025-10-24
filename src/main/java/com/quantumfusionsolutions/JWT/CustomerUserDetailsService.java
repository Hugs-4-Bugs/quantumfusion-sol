package com.quantumfusionsolutions.JWT;

import com.quantumfusionsolutions.model.User;
import com.quantumfusionsolutions.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername: {}", username);

        userDetail = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // ✅ Add ROLE_ prefix as Spring Security requires it
        String role = "ROLE_" + userDetail.getRole().toUpperCase();

        return org.springframework.security.core.userdetails.User
                .withUsername(userDetail.getEmail())
                .password(userDetail.getHashedPassword())
                .authorities(List.of(new SimpleGrantedAuthority(role)))
                .build();
    }

    public User getUserDetail() {
        return userDetail;
    }
}
