package com.learn.project.journal;

import com.learn.project.journal.repository.UserRepoImpl;
import com.learn.project.journal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JournalApplication implements CommandLineRunner {

    @Autowired
    UserRepoImpl userRepo;

    @Autowired
    EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        System.out.println(userRepo.getUserFor());
//        emailService.sendEmail("laxmishalekha123@gmail.com","test sub","Hello, how are you? Thanks,");
    }
}
//In SQL (JPA/Hibernate):
//Spring Boot auto-configures transaction management for JPA.
//So, you don't need @EnableTransactionManagement explicitly — it's already included in the auto-config

//In MongoDB:
//Spring Boot does not auto-configure transaction management for MongoDB by default, because:
//Transactions in MongoDB require replica sets or sharded clusters.
//Not all MongoDB use cases need transactions.

//Transactions only work in MongoDB if it's running as a replica set.
//Set this up locally with:
//
//mongod --replSet rs0 --bind_ip localhost
//
//Then in the Mongo shell:
//
//rs.initiate()