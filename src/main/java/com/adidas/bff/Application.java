package com.adidas.bff;

import com.adidas.bff.exceptions.FeignErrorDecoder;
import com.adidas.bff.exceptions.customExceptions.BadRequestException;
import com.adidas.bff.exceptions.customExceptions.MethodForbidenException;
import com.adidas.bff.exceptions.customExceptions.MethodNotAllowedException;
import com.adidas.bff.exceptions.customExceptions.ResourceNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@ComponentScan("com.adidas.bff")
@EnableConfigurationProperties
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Primary
	public FeignErrorDecoder errorDecoder(){return new FeignErrorDecoder();}

}
