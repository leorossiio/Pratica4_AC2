// src/main/java/com/example/Pratica4/config/SpringDocConfig.java
package com.example.Pratica4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pratica 4 - AC2 (Andrea Leles DevOpsQA)")
                        .version("1.0")
                        .description("API de Alunos seguindo Clean Architecture e DDD."));
    }
}