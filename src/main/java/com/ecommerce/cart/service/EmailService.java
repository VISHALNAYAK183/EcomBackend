package com.ecommerce.cart.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        System.out.println("Hlo");
        try {
            System.out.println("🔄 Attempting to send OTP to: " + toEmail);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp);
            message.setFrom("vishunyk108@gmail.com"); // Important

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.out.println("❌ Failed to send email");
            e.printStackTrace(); // This will reveal the root cause
        }
    }
}

