package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

/**
 * Swagger/OpenAPI 文档元信息配置（标题、版本、描述等）
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(
                new Info()
                        .title("Demo 接口文档")
                        .description("Spring Boot + MyBatis-Plus + Sa-Token 示例项目接口")
                        .version("v1.0.0")
                        .contact(new Contact().name("admin").email("admin@example.com"))
        );
    }
}
