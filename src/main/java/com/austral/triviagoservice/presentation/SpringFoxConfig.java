package com.austral.triviagoservice.presentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder().title("Triviago API Documentation")
                .description("Documentation for endpoints (:")
                .contact(new Contact("Triviago-Team", "https://www.payment.example.com/", "nzuwera2002@gmail.com"))
                .version("1.0.0")
                .build();
    }
}
