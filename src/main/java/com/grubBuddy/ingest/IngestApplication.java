package com.grubBuddy.ingest;

import com.grubBuddy.ingest.service.FoodService;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import javax.annotation.Resource;

@SpringBootApplication
@Resource(name = "application.properties")
@ComponentScan(basePackages = "com.grubBuddy.ingest")
@EntityScan(basePackages = "com.grubBuddy.ingest")
public class IngestApplication {
	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private FoodService foodService;

	public static void main(String[] args) {
		SpringApplication.run(IngestApplication.class, args);
	}

	@Bean
	public ReactiveMongoTemplate reactiveFoodTemplate() {
		ReactiveMongoTemplate template = new ReactiveMongoTemplate(mongoClient, "FoodComposition");

		return template;
	}
}
