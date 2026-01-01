package com.thiago.abandonedcode.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AbandonedCode API")
                        .version("1.0.0")
                        .description("API RESTful para sistema de blog pessoal com categorias hierárquicas")
                        .contact(new Contact()
                                .name("Thiago Monteiro")
                                .url("http://localhost:8080")
                        )
                );
    }
}
