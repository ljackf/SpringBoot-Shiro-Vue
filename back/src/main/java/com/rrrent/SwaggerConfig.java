package com.rrrent;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/3/11
 */
@Configuration
//启用swagger
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger接口暴露开关
     */
    @Value("${swagger.enable}")
    private boolean swaggerEnable;

    /**
     * 创建API应用
     */
    @Bean
    public Docket swaggerSpringMvcPlugin(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("L-TOKEN").description("用户token名")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerEnable).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.withMethodAnnotation
                (ApiOperation.class)).build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口文档").
                description("雷雷租房服务端通用接口").version("1.0").build();
    }
}
