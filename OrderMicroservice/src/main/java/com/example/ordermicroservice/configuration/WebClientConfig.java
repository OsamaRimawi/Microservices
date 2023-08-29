package com.example.ordermicroservice.configuration;

import com.example.ordermicroservice.mapper.OrderDTOMapper;
import com.example.ordermicroservice.mapper.OrderDTOMapperImpl;
import com.example.ordermicroservice.mapper.OrderLineItemsMapper;
import com.example.ordermicroservice.mapper.OrderLineItemsMapperImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced

    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
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

