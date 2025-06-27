package com.quickShop.cart.service;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.CartItem;

public interface CartService {
    public Cart addToCart(CartItem item);
    public Cart getCartById(Long id);
    public Cart removeItem(Long cartId, Long itemId);
   public Cart updateItemQuantity(Long cartId, Long itemId, int quantity);
}
