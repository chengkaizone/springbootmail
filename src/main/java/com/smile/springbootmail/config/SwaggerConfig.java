package com.smile.springbootmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.smile.springbootmail.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                .description("SpringBoot测试文档")
                .contact(new Contact("江南一点雨", "http://baidu.com", "1234@163.com"))
                        .version("v1.0")
                .title("API测试文档")
                .license("Apache2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build());
    }

}
