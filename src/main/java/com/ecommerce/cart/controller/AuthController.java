package com.ecommerce.cart.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.entity.User;
import com.ecommerce.cart.repository.UserRepository;
import com.ecommerce.cart.service.OtpService;


  @RestController
  @CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        String contact = request.get("contact");

        if (contact == null || contact.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Contact required"));
        }

        String status = otpService.sendOtp(contact);

        if ("otp_sent".equals(status)) {
            return ResponseEntity.ok(Map.of("status", "Y"));
        } else {
            return ResponseEntity.ok(Map.of("status", "N"));
        }
    }

     @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> payload) {
        String contact = payload.get("contact");
        String otp = payload.get("otp");

        Optional<User> optionalUser = userRepository.findByEmailOrMobile(contact, contact);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getOtp().equals(otp)) {
                // Optional: check expiry with otpCreatedAt
                user.setIsVerified(true);
                userRepository.save(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Invalid OTP"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "User not found"));
        }
    }
}
