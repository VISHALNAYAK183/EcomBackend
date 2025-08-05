package com.ecommerce.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.cart.dto.OtpRequest;
import com.ecommerce.cart.entity.User;
import com.ecommerce.cart.repository.UserRepository;
import com.ecommerce.cart.service.EmailService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class OtpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody OtpRequest request) {
        String input = request.getEmailOrMobile();
        Optional<User> userOpt = input.contains("@")
                ? userRepository.findByEmail(input)
                : userRepository.findByMobile(input);

        if (userOpt.isPresent()) {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            User user = userOpt.get();
            user.setOtp(otp);
            user.setOtpCreatedAt(LocalDateTime.now());
            userRepository.save(user);

            if (input.contains("@")) {
                emailService.sendOtpEmail(user.getEmail(), otp);
            }

            return ResponseEntity.ok("OTP sent successfully");
        } else {
            return ResponseEntity.status(404).body("User not found, redirect to registration");
        }
    }
}