package com.quickShop.cart.controller;

import com.quickShop.cart.Entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private Product product;

    @GetMapping("/api/products")
    public List<product> getAllProducts() {
        return ;
    }
}
