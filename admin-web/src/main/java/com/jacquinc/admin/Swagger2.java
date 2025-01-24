package com.jacquinc.admin;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 配置
 *
 * @author cjq
 * created on  2020/01/02
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2 {

    /*@Value("${app.host}")
    public String host;*/

    @Value("${app.swagger}")
    private boolean swaggerEnable;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Admin System")
                .description("Admin System")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        List<Parameter> globalOperationParameters = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(globalOperationParameters)
                .enable(swaggerEnable)
                /*.host(host)*/
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
}
