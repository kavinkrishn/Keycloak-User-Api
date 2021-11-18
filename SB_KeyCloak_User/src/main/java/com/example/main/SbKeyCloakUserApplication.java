package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EntityScan("com.example.model")
@ComponentScan("com.example")
//@EnableJpaRepositories("com.example.repository")
public class SbKeyCloakUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbKeyCloakUserApplication.class, args);
	}

	 
}
