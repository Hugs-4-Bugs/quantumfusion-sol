package com.quantumfusionsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Otp {

    @Id
    private String otpId;
    private String userId;
    private String otpCode;
    private Timestamp expiresAt;
    private boolean used;
    private String purpose;

    // Getters and setters
}