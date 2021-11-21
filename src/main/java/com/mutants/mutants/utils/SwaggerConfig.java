package com.mutants.mutants.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {

    /**
     * Generate documentation API
     * See doc in interface --> "http://localhost:8080/swagger-ui.html#/"
     * See doc in JSON --> "http://localhost:8080/v2/api-docs/"
     * @return Return view or json for import in swagger Editor
     */
    @Bean
    public Docket customImplement() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mutants.mutants"))
                .paths(PathSelectors.any())
                .build();
    }

}
