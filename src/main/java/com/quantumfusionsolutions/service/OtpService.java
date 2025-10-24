package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.Otp;
import com.quantumfusionsolutions.repository.OtpRepository;
import com.quantumfusionsolutions.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailUtil emailUtil;

    public void sendSignupOtp(String email) {
        // Generate OTP
        String otpCode = String.format("%06d", new Random().nextInt(999999)); // 6 digit random OTP.

        Otp otp = new Otp();
        otp.setOtpId(UUID.randomUUID().toString());
        otp.setEmail(email);
        otp.setOtpCode(otpCode);
        otp.setPurpose("signup");
        otp.setUsed(false);

        otp.setExpiresAt(Timestamp.from(Instant.now().plusSeconds(600))); // 10 min

        otpRepository.save(otp);

        // Send OTP email
        emailUtil.sendOtpEmail(email, otpCode);
    }

    public boolean verifyOtp(String email, String otpCode) {
        Optional<Otp> optionalOtp = otpRepository.findByEmailAndPurposeAndUsedFalse(email, "signup");

        if (optionalOtp.isPresent()) {
            Otp otp = optionalOtp.get();
            if (otp.getOtpCode().equals(otpCode) && otp.getExpiresAt().after(Timestamp.from(Instant.now()))) {
                otp.setUsed(true);
                otpRepository.save(otp);
                return true;
            }
        }
        return false;
    }
}

// refereance link:  https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.query-methods.query-property-expressions
