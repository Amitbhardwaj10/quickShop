package com.quickShop.cart.controller;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;
import com.quickShop.cart.repository.CartRepository;
import com.quickShop.cart.service.CartService;
import com.quickShop.cart.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    @Autowired
    private StripeService stripeService;

    @PostMapping("/create")
    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{cartId}/item/{itemId}")
    public Cart updateQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam int quantity) {
        return cartService.updateItemQuantity(cartId, itemId, quantity);
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    public Cart removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        return cartService.removeItem(cartId, itemId);
    }

    @DeleteMapping("/clear-all")
    public ResponseEntity<String> clearAllCarts() {
        cartRepository.deleteAll();
        return ResponseEntity.ok("All carts and cart items deleted.");
    }

    @GetMapping("/{cartId}/checkout")
    public ResponseEntity<?> checkout(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            String checkoutUrl = stripeService.createCheckoutSession(cart);
            return ResponseEntity.ok(Map.of("url", checkoutUrl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

