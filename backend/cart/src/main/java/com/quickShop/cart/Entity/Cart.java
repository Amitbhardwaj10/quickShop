package com.quickShop.cart.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total = 0.0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id") // Creates foreign key in CartItem table
    private List<CartItem> items = new ArrayList<>();
}
