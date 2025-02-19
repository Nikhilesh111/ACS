package com.example.cloudmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.cloudmanagementsystem.repository")  // ✅ Add this line
@EntityScan("com.example.cloudmanagementsystem.entity")
public class CloudManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudManagementSystemApplication.class, args);
    }
}
