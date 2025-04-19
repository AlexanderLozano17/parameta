package com.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WsSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsSoapApplication.class, args);
	}

}
