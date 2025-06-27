package com.quickShop.cart.service;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;

public interface CartService {
    public Cart addToCart(CartItem item);
    public Cart getCartById(Long id);
}
