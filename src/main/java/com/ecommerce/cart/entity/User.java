package com.ecommerce.cart.entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_created_at")
    private LocalDateTime otpCreatedAt;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;




    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public LocalDateTime getOtpCreatedAt() { return otpCreatedAt; }
    public void setOtpCreatedAt(LocalDateTime otpCreatedAt) { this.otpCreatedAt = otpCreatedAt; }

    public Boolean getIsVerified() {
    return isVerified;
}

public void setIsVerified(Boolean isVerified) {
    this.isVerified = isVerified;
}

}
