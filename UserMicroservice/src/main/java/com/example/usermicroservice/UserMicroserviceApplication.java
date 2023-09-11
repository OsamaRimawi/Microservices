package com.example.usermicroservice;

import com.example.usermicroservice.mapper.UserDTOMapper;
import com.example.usermicroservice.mapper.UserDTOMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    @Bean
    public UserDTOMapper userDTOMapper() {
        return new UserDTOMapperImpl();
    }

}
