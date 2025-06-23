
package com.ecommerce.cart.service;

import com.ecommerce.cart.entity.users;
import com.ecommerce.cart.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
private EmailService emailService; 
    public String sendOtp(String contact) {
        String otp = String.valueOf(new Random().nextInt(899999) + 100000); // 6-digit OTP
        Optional<users> userOpt;

        if (contact.contains("@")) {
            userOpt = userRepository.findByEmail(contact);
        } else {
            userOpt = userRepository.findByMobile(contact);
        }

        if (userOpt.isPresent()) {
            users user = userOpt.get();
            user.setOtp(otp);
            user.setOtpCreatedAt(LocalDateTime.now());
            userRepository.save(user);
  if (contact.contains("@")) {
                emailService.sendOtpEmail(contact, otp); // 🎯 THIS was missing
            }
            // TODO: Integrate actual email/SMS sender
            System.out.println("OTP sent to: " + contact + " is " + otp);

            return "otp_sent";
        } else {
            return "not_registered";
        }
    }
}

