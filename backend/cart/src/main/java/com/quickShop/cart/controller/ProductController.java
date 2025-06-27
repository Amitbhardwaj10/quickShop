package com.quickShop.cart.controller;

import com.quickShop.cart.Entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "iPhone 14 Pro", "Apple smartphone with A16 Bionic chip", 1199.99, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-model-unselect-gallery-1-202209?wid=5120&hei=2880&fmt=jpeg&qlt=80&.v=1660753619946"),
                new Product(2L, "Samsung Galaxy S23", "Flagship Android phone with triple camera", 999.99, "https://images.samsung.com/is/image/samsung/p6pim/in/galaxy-s23/gallery/in-galaxy-s23-s911-sm-s911bzgcins-thumb-535234196"),
                new Product(3L, "MacBook Air M2", "Lightweight Apple laptop with M2 chip", 1399.00, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-13-midnight-select-20220606?wid=4000&hei=3072&fmt=jpeg&qlt=90&.v=1654122880566"),
                new Product(4L, "Dell XPS 13", "Compact Windows laptop with 12th Gen Intel", 1149.50, "https://i.dell.com/sites/csimages/Master_Imagery/all/xps-13-9310-laptop.jpg"),
                new Product(5L, "Sony WH-1000XM5", "Noise-cancelling over-ear headphones", 349.99, "https://m.media-amazon.com/images/I/61D42rZ3b7L._AC_SL1500_.jpg"),
                new Product(6L, "Logitech MX Master 3S", "Wireless ergonomic mouse for productivity", 99.99, "https://resource.logitech.com/w_1600,h_1600,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/mice/mx-master-3s/gallery/mx-master-3s-top-view-graphite.png"),
                new Product(7L, "Apple Watch Series 8", "Smartwatch with fitness tracking", 499.00, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/watch-series8-hero-41mm-202209_GEO_IN?wid=2000&hei=2000&fmt=jpeg&qlt=95&.v=1661958596055"),
                new Product(8L, "iPad Air (5th Gen)", "Tablet with Apple M1 chip", 599.00, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-air-select-wifi-starlight-202203_GEO_US?wid=940&hei=1112&fmt=png-alpha&.v=1645066936505"),
                new Product(9L, "Nintendo Switch", "Portable hybrid gaming console", 299.99, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/NintendoSwitchConsole.jpg/800px-NintendoSwitchConsole.jpg"),
                new Product(10L, "Kindle Paperwhite", "E-reader with adjustable warm light", 129.99, "https://m.media-amazon.com/images/I/61MeOeDSAUL._AC_SL1000_.jpg"),
                new Product(11L, "Google Pixel 7", "Android phone with clean UI and great camera", 649.00, "https://store.google.com/product/images/phone_google_pixel_7_black.jpg"),
                new Product(12L, "Bose SoundLink Flex", "Portable Bluetooth speaker with rich audio", 149.00, "https://m.media-amazon.com/images/I/81pTb+SBYEL._AC_SL1500_.jpg")
        );

    }
}
