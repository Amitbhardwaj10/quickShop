package com.quickShop.cart.controller;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;
import com.quickShop.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);

        if (cart.getItems().isEmpty()) {
            return ResponseEntity.ok("Cart is empty");
        }

        return ResponseEntity.ok(cart);
    }
}
