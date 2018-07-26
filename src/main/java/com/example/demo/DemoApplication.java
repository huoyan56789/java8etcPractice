package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 它是一个组合注解，该注解组合了：
 * @Configuration、
 * @EnableAutoConfiguration、
 * SpringBoot 还会自动扫描 @SpringBootApplication 所在类的同级包以及下级包里的 Bean
 * @EnableAutoConfiguration 让 Spring Boot 根据类路径中的 jar 包依赖为当前项目进行自动配置
 * @ComponentScan
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
