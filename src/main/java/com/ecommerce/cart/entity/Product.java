package com.ecommerce.cart.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dashboard")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int category;
    private int type;
    private String imgurl;
    private String heading;
    private String subheading;
    private double price;
    private String hotdeal;
    private String brand;
    private double discount;
    private int status;
}
