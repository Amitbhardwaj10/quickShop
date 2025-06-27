package com.quickShop.cart.service;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;
import com.quickShop.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(CartItem item) {
        Cart cart = cartRepository.findById(1L).orElseGet(() -> new Cart());
        cart.getItems().add(item);
        item.setCart(cart);

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        cart.setTotal(total);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Cart removeItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));

        double total = cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        cart.setTotal(total);

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemQuantity(Long cartId, Long itemId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        for(CartItem item : cart.getItems()) {
            if(item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        cart.setTotal(total);

        return cartRepository.save(cart);
    }


}
