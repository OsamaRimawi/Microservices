package com.example.apigateway.configuration;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {


        serverHttpSecurity.csrf()
                .disable()
                .oauth2ResourceServer()
                .jwt();

        serverHttpSecurity.oauth2Client()
                .and()
                .oauth2Login();

        serverHttpSecurity.authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec.pathMatchers("/eureka/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated())

                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        return serverHttpSecurity.build();


    }
}