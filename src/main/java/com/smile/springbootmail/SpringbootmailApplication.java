package com.smile.springbootmail;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableBatchProcessing
@EnableAdminServer
public class SpringbootmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmailApplication.class, args);
    }

}
