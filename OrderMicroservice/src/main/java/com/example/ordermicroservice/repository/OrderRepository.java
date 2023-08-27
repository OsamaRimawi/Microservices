package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderNumber(String orderNumber);

    void deleteOrderByOrderNumber(String orderNumber);
}
