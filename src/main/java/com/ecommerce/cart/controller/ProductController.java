package com.ecommerce.cart.controller;

import com.ecommerce.cart.entity.Product;
import com.ecommerce.cart.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // 🔥 ADD THIS METHOD TO FIX YOUR ISSUE
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id); // This method must exist in your ProductService
    }
}
