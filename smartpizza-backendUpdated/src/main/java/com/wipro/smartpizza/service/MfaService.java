package com.wipro.smartpizza.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MfaService {
 
    private final UserRepository userRepository;
 
    public String generateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "User not found!"));
        String otp = String.format("%06d",
                new Random().nextInt(999999));
        user.setOtpCode(otp);
        userRepository.save(user);
        System.out.println("OTP for " + email + " : " + otp);
        return "OTP generated: " + otp;
    }
 
    public boolean verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "User not found!"));
        if (user.getOtpCode() != null &&
                user.getOtpCode().equals(otp)) {
            user.setOtpCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    
}
 