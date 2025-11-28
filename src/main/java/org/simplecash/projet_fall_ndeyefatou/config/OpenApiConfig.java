package org.simplecash.projet_fall_ndeyefatou.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SimpleCash Banking API",
                version = "1.0",
                description = "API de gestion des comptes, conseillers, clients et opérations.",
                contact = @Contact(
                        name = "Ndeye Fatou FALL",
                        email = "contact@simplecash.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/swagger-ui/index.html#/", description = "Serveur local")
        }
)
public class OpenApiConfig {
}
