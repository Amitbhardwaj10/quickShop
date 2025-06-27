package com.quickShop.cart.repository;

import com.quickShop.cart.Entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {

}
