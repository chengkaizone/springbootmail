package com.smile.springbootmail;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbootmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmailApplication.class, args);
    }

}