package com.example.ordermicroservice.controller;


import com.example.ordermicroservice.DTO.OrderDTO;
import com.example.ordermicroservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller Class that handles CRUD Operations for Orders
 */
@RestController
@RequestMapping("${order.api.base-url}")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {

        this.orderService = orderService;

    }

    /**
     * Performs Get ALL operation .
     *
     * @return All Orders in Database
     */
    @GetMapping("/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAll();
    }

    /**
     * Performs Get Order operation .
     *
     * @param orderNumber The number of the order.
     * @return order in Database
     */
    @GetMapping("")
    public OrderDTO getOrderByOrderNumber(
            @RequestParam String orderNumber) {
        return orderService.getOrderByOrderNumber(orderNumber);
    }

    /**
     * Performs Create Order operation .
     *
     * @param orderDTO The Order DTO Json
     * @return responseEntityCreated
     */
    @PostMapping
    public ResponseEntity<String> addOneOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    /**
     * Performs Update Order operation .
     *
     * @param orderDTO The Order DTO Json
     * @return responseEntityUpdated
     */
    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    /**
     * Performs Delete Order operation .
     *
     * @param orderNumber The Order DTO Json
     * @return responseEntityDeleted
     */
    @DeleteMapping
    public ResponseEntity<String> deleteOrder(
            @RequestParam String orderNumber) {
        return orderService.deleteOrderByOrderNumber(orderNumber);
    }

}
