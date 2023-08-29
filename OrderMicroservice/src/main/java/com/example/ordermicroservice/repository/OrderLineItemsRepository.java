package com.example.ordermicroservice.repository;


import com.example.ordermicroservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
    void deleteOrderLineItemsByOrderId(Integer orderId);

}
