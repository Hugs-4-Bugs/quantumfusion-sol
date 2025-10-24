package com.quantumfusionsolutions.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String otpId;

    // Make this optional since user may not exist yet (signup OTP)
//    @Column(nullable = true)
//    private String userId;

    @Column(nullable = false)
    private String otpCode;

    @Column(nullable = false)
    private Timestamp expiresAt;

    @Column(nullable = false)
    private boolean used = false;

    @Column(nullable = false)
    private String purpose; // e.g., "SIGNUP", "FORGOT_PASSWORD"

    @Column(nullable = false)
    private String email;
}
