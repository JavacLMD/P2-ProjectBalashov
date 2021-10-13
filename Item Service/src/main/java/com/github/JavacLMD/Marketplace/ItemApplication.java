package com.github.JavacLMD.Marketplace;

import com.github.JavacLMD.Marketplace.controller.ItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemApplication implements CommandLineRunner {

	@Autowired
	private ItemController controller;

	public static void main(String[] args) {
		SpringApplication.run(ItemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// save list Customers
		controller.itemService.generate().subscribe(x -> System.out.println(">> Generated Row: " + x));
	}

}
