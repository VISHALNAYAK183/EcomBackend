package com.ecommerce.cart.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.service.OtpService;


  @RestController
  @CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

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
}
