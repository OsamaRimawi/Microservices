package com.example.productmicroservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Osama"
                ),
                description = "API Documentation For Product service"
        ,title = "Product API Documentation",
                version ="v1.0"

        ),
        servers = {
                @Server(
                        description = "product",
                        url = "http://localhost:9002"
                ),
                @Server(
                        description = "order",
                        url = "http://localhost:9001"
                ),
                @Server(
                        description = "inventory",
                        url = "http://localhost:9003"
                )
        }
)
public class OpenApiConfig {
}
