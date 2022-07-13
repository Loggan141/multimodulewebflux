package com.forttiori.multimodulereactivewebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;


@Configuration
@EnableSwagger2
//@ComponentScan("com.v1.truck")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.forttiori.multimodulereactivewebflux."))
                .paths(any())
                .build();
    }

      private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Trucks API - WebFluxed")
                .description("Documentação da API Truck em WebFlux ")
                .version("1.0.0")
                .contact(new Contact("Felipe Loggan", null, "felipe_loggan@hotmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

}



