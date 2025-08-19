package com.example.myProducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter2;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class myProductsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(myProductsApplication.class, args);
	}

}