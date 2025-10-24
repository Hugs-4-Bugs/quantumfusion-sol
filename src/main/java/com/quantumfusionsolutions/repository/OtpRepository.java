package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findByEmailAndPurposeAndUsedFalse(String email, String purpose);
}

