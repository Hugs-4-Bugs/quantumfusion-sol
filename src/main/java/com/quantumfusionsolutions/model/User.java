package com.quantumfusionsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;
    private String fullName;
    private String email;
    private String hashedPassword;
    private boolean emailVerified;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}