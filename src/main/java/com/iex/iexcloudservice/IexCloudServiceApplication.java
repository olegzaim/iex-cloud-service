package com.iex.iexcloudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@EnableJpaRepositories(basePackages = "com.iex.iexcloudservice.repository")
@SpringBootApplication
public class IexCloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IexCloudServiceApplication.class, args);
    }

}
