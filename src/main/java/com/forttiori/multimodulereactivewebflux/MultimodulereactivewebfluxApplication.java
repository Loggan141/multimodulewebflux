package com.forttiori.multimodulereactivewebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import repository.TruckRepository;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackageClasses = TruckRepository.class)
public class MultimodulereactivewebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultimodulereactivewebfluxApplication.class, args);
	}

}
