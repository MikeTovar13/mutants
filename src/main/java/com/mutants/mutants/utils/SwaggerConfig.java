package com.mutants.mutants.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Generate documentation API
     * See doc in interface -> http://localhost:8080/api/swagger-ui.html#/
     * See doc in JSON -> http://localhost:8080/api/v2/api-docs
     * @return View or Json for import in Swagger editor
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mutants.mutants"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Function for custom info in documentation
     * @return data update in swagger documentation
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("basicScheme",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Mutants API Verify")
                        .version("1.0")
                        .license(new License()
                                .name("License MIT")
                                .url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("Michael Tovar")
                                .email("ferney.tovar13@gmail.com"))
                        .description("Micro services from verify DNA mutant" +
                                "[ Base URL: http://localhost:8080/api/swagger-ui.html#/ or " +
                                "https://black-alpha-333602.uc.r.appspot.com/api/swagger-ui.html#/ ]\n "));
    }
}
