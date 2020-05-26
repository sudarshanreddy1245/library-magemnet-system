package com.librarymanagementsystem.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.librarymanagementsystem.example")
@EnableJpaRepositories(basePackages = { "com.librarymanagementsystem.example.dao" })
@EntityScan(basePackages = { "com.librarymanagementsystem.example.entity" })
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
