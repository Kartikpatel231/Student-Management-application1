package com.mycompany.studentmanagementapp.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@EnableSwagger2
@Configuration
public class SwaggerConfig  {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select().apis(withClassAnnotation(Api.class)).paths(PathSelectors.any()).build();
    }
}
