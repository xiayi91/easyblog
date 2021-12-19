package com.yiblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        //return abstraction of document
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()).enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yiblog"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("yiblog")
                .description("a blog")
                .contact(new Contact("Yi Xia", "http://54.177.15.182", "yi.xia.104@my.csun.edu"))
                .version("1.0")
                .build();
    }

    //encapsulate global request parameters
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("uuid")
                .description("equipment uuid")
                .required(true)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    private List<Response> getGlobalResponseMessage(){
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("cannot find the resource").build());
        return responseList;
    }

}
