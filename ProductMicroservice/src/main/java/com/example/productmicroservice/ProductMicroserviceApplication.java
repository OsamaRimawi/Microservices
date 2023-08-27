package com.example.productmicroservice;

import com.example.productmicroservice.mapper.ProductDTOMapper;
import com.example.productmicroservice.mapper.ProductDTOMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroserviceApplication.class, args);
    }
    @Bean
    public ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapperImpl();
    }

}
