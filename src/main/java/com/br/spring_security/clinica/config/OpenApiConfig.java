package com.br.spring_security.clinica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful da Clínica")
                        .version("v1")
                        .description("API RESTful para gerenciamento de agendamentos, médicos, pacientes e especialidades de uma clínica.")
                        .termsOfService("https://github.com/Davialves22/clinica-api.git")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Davialves22/clinica-api.git")
                        ));
    }
}