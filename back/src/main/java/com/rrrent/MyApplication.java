package com.rrrent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: jack
 * @description: SpringBoot启动类
 * @date: 2020/06/18
 */
@SpringBootApplication
@EnableAutoConfiguration
@RequestMapping
@Import(SwaggerConfig.class)
@MapperScan("com.rrrent.theia.dao")
public class MyApplication extends SpringBootServletInitializer {

	public static void main(String[] args){
		SpringApplication.run(MyApplication.class,args);
	}
}
