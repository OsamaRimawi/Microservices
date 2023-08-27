package com.example.ordermicroservice;

import com.example.ordermicroservice.mapper.OrderDTOMapper;
import com.example.ordermicroservice.mapper.OrderDTOMapperImpl;
import com.example.ordermicroservice.mapper.OrderLineItemsMapper;
import com.example.ordermicroservice.mapper.OrderLineItemsMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMicroserviceApplication.class, args);
    }

    @Bean
    public OrderDTOMapper orderDTOMapper() {
        return new OrderDTOMapperImpl();
    }

    @Bean
    public OrderLineItemsMapper orderLineItemsMapper() {
        return new OrderLineItemsMapperImpl();
    }
}