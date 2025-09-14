package com.learn.project.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}//This bean lets Spring know how to manage MongoDB transactions behind the scenes

}
//In SQL (JPA/Hibernate):
//Spring Boot auto-configures transaction management for JPA.
//So, you don't need @EnableTransactionManagement explicitly — it's already included in the auto-config

//In MongoDB:
//Spring Boot does not auto-configure transaction management for MongoDB by default, because:
//Transactions in MongoDB require replica sets or sharded clusters.
//Not all MongoDB use cases need transactions.