package com.quantumfusionsolutions.JWT;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    // ✅ Public endpoints (no authentication required)
    private static final List<String> PUBLIC_URLS = List.of(
            "/api/auth/",                   // covers all /api/auth/** endpoints
            "/api/users/login",
            "/api/users/forgotPassword",
            "/api/users/checkToken",
            "/api/users/changePassword",    // if you want to allow unauthenticated access; otherwise remove

            // Swagger/OpenAPI
            "/swagger-ui/",
            "/v3/api-docs",
            "/webjars/",
            "/swagger-resources",
            "/swagger-ui.html"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        try {
            // ✅ Allow preflight requests and public endpoints
            if ("OPTIONS".equalsIgnoreCase(request.getMethod()) || isPublicEndpoint(path)) {
                filterChain.doFilter(request, response);
                return;
            }

            // ✅ Extract JWT token
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtUtil.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

                    if (jwtUtil.validateToken(token, userDetails)) {
                        Claims claims = jwtUtil.extractAllClaims(token);
                        String role = (String) claims.get("role");

                        // ✅ Map role to authorities
                        List<SimpleGrantedAuthority> authorities =
                                List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
        }
    }

    private boolean isPublicEndpoint(String path) {
        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
    }

    // ✅ Helper methods for role checks
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean isUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
    }

    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;
        return auth.getName();
    }
}



















//package com.quantumfusionsolutions.JWT;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private CustomerUserDetailsService customerUserDetailsService;
//
//    private final ThreadLocal<Claims> threadLocalClaims = new ThreadLocal<>();
//    private final ThreadLocal<String> threadLocalUsername = new ThreadLocal<>();
//
//    private static final List<String> PUBLIC_URLS = List.of(
//            "/api/auth/login",
//            "/api/auth/signup/sendOtp",
//            "/api/auth/signup/verifyOtp",
//            "/api/auth/forgotPassword"
//    );
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String path = request.getServletPath();
//
//        try {
//            // Skip public endpoints & CORS preflight
//            if ("OPTIONS".equalsIgnoreCase(request.getMethod()) || isPublicEndpoint(path)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            String authorizationHeader = request.getHeader("Authorization");
//            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                String token = authorizationHeader.substring(7);
//                String username = jwtUtil.extractUsername(token);
//
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
//
//                    if (jwtUtil.validateToken(token, userDetails)) {
//                        Claims claims = jwtUtil.extractAllClaims(token);
//                        threadLocalUsername.set(username);
//                        threadLocalClaims.set(claims);
//
//                        UsernamePasswordAuthenticationToken authToken =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authToken);
//                    }
//                }
//            }
//
//            filterChain.doFilter(request, response);
//
//        } finally {
//            // Always clear ThreadLocals
//            threadLocalUsername.remove();
//            threadLocalClaims.remove();
//        }
//    }
//
//    private boolean isPublicEndpoint(String path) {
//        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
//    }
//
//
//    // Role helpers
//    public boolean isAdmin() {
//        Claims claims = threadLocalClaims.get();
//        return claims != null && "admin".equalsIgnoreCase((String) claims.get("role"));
//    }
//
//    public boolean isUser() {
//        Claims claims = threadLocalClaims.get();
//        return claims != null && "user".equalsIgnoreCase((String) claims.get("role"));
//    }
//
//    public String getCurrentUser() {
//        return threadLocalUsername.get();
//    }
//}
