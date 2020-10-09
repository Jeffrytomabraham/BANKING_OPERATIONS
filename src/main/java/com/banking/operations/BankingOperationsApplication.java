package com.banking.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.banking.operations.*")
@EnableSwagger2
public class BankingOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingOperationsApplication.class, args);
	}

}
