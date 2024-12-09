package com.personalfinancial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.personalfinancial.repositories") 
public class PersonalFinancialManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinancialManagementApplication.class, args);
	}

}
