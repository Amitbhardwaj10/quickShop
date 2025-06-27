package com.quickShop.cart.controller;

import com.quickShop.cart.Entity.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "iPhone 14 Pro", "Apple smartphone with A16 Bionic chip", 1199.99, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-model-unselect-gallery-1-202209?wid=5120&hei=2880&fmt=jpeg&qlt=80&.v=1660753619946"),
                new Product(2L, "Samsung Galaxy S23", "Flagship Android phone with triple camera", 999.99, "https://rukminim2.flixcart.com/image/416/416/xif0q/mobile/t/0/g/-original-imah4zp7fvqp8wev.jpeg?q=70&crop=false"),
                new Product(3L, "MacBook Air M2", "Lightweight Apple laptop with M2 chip", 1399.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA1XYbO9LRL7iMnFEg1YEzwOMIIWGSl5ZvzRjwaL5_5qcIo8JUVtEw-ANPMDtqp3Tykgk"),
                new Product(4L, "Sony WH-1000XM5", "Noise-cancelling over-ear headphones", 349.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCQOxrVtBr3Oe-6LDfdYlc1z06y1qK578G_MZYivGDYw2pj0tbSBoN8ryHK2xCGlYZT5w"),
                new Product(5L, "Logitech MX Master 3S", "Wireless ergonomic mouse for productivity", 99.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxduQ0xf2zTozt0gDw_51tbHxTwnqeLpiskEi_GDccRLSz7YgTL1kGYz-lqqR8ORnPlTw"),
                new Product(6L, "Apple Watch Series 8", "Smartwatch with fitness tracking", 499.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHH6Yr1QzU6EKFduSM_HpXDOXyasqnivGCUIIh4BBzLBcVO98nzZ5qi_izyBMBSnzYEdA"),
                new Product(7L, "Nintendo Switch", "Portable hybrid gaming console", 299.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWY5kR9MBOeefvGvvgCpz_T2ERbDI_0OxgY3I_G-3YdGR2tnwpr3ozfUl5sIbLagZpbrM&usqp=CAU"),
                new Product(8L, "Google Pixel 7", "Android phone with clean UI and great camera", 649.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR09Qxc1zGKJOpcldPya6fRaKII9aNF1Y6LwcwVdEM0lLtZZaGNJVCUVBheEU2DUE9sIP4&usqp=CAU")
        );

    }
}
