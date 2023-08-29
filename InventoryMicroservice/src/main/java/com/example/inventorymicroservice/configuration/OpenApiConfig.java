package com.example.inventorymicroservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Osama"
                ),
                description = "API Documentation For Inventory service"
                , title = "Inventory API Documentation",
                version = "v1.0"

        ),
        servers = {
                @Server(
                        description = "inventory",
                        url = "http://localhost:9003"
                ),
                @Server(
                        description = "order",
                        url = "http://localhost:9001"
                ),
                @Server(
                        description = "product",
                        url = "http://localhost:9002"
                )
        }
)
public class OpenApiConfig {
}
