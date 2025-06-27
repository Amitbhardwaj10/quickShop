package com.quickShop.cart.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long Id;
    private String title;
    private Double price;
    private String description;
    private String imageUrl;
}
