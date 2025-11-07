package com.codeit.springwebbasic.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components());
    }

    private Info apiInfo() {
        return new Info()
                .title("도서 대여 시스템 API")
                .description("도서 대여에 관련한 여러가지 기능을 제공합니다.")
                .version("1.8.0");
    }
}
