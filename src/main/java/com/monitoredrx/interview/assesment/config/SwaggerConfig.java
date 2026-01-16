package com.monitoredrx.interview.assesment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerConfigDocumentAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Interview Assessment API")
                .version("1.0")
                .description("Interview Assessment API")
                .contact(new Contact()
                        .name("Jithmi S. Arachchi")

                ) );
    }
}
