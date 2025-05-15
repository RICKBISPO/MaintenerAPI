package com.simples.maintainer.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MaintenerAPI")
                        .version("1.0")
                        .description("MaintenerAPI")
                        )
                .components(new io.swagger.v3.oas.models.Components()
        );
    }

}
