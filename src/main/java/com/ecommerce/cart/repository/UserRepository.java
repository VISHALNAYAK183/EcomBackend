package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.User;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);
    Optional<User> findByEmailOrMobile(String email, String mobile);
}
