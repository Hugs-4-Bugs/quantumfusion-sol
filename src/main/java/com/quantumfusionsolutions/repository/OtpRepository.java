package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {

    Otp findByEmailAndOtpCode(String email, String otpCode);

}
