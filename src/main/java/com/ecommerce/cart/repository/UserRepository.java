package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.users;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<users, Long> {
    Optional<users> findByEmail(String email);
    Optional<users> findByMobile(String mobile);
}
