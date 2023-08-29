package com.example.productmicroservice.configuration;

import com.example.productmicroservice.mapper.ProductDTOMapper;
import com.example.productmicroservice.mapper.ProductDTOMapperImpl;
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
    public ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapperImpl();
    }
}
