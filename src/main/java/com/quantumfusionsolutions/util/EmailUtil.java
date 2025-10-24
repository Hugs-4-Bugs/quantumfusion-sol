package com.quantumfusionsolutions.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {

    @Autowired
    private JavaMailSender emailSender;

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mailtoprabhat72@gmail.com");   // replace with original mail "contact@quantumfusionsolutions.com"
        message.setTo(to);
        message.setSubject("Your OTP for QuantumFusionSol Signup");
        message.setText("Your OTP is: " + otp + " (valid for 10 minutes)");
        emailSender.send(message);
    }
}
