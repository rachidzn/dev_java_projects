package com.yaps.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.yaps.petstore.api"})
//@ComponentScan(basePackages = {"com.yaps.petstore","com.barkBank"})
public class Tp11Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Tp11Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Tp11Application.class, args);
	}

}
