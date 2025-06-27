package com.quickShop.cart.service;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;
import com.quickShop.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart addToCart(CartItem item) {
        if (item.getCartId() == null) {
            throw new RuntimeException("Cart ID is missing");
        }

        Cart cart = cartRepository.findById(item.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().add(item);

        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        cart.setTotal(total);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Cart removeItem(Long cartId, Long itemId) {
        Cart cart = getCartById(cartId);
        cart.getItems().removeIf(i -> i.getId().equals(itemId));
        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemQuantity(Long cartId, Long itemId, int quantity) {
        Cart cart = getCartById(cartId);
        cart.getItems().forEach(i -> {
            if (i.getId().equals(itemId)) {
                i.setQuantity(quantity);
            }
        });
        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    private void updateCartTotal(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        cart.setTotal(total);
    }
}
