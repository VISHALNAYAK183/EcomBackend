package com.ecommerce.cart.service;

import com.ecommerce.cart.dto.RegisterRequest;
import com.ecommerce.cart.entity.User;
import com.ecommerce.cart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

        if (userRepository.findByMobile(request.getMobile()).isPresent()) {
            return "Mobile already registered";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setOtp(null); // Can skip if using OTP later
        user.setOtpCreatedAt(null);

        userRepository.save(user);
        return "User registered successfully!";
    }
}
