package com.example.cloudmanagementsystem.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration  // ✅ Marks this as a configuration class
public class GoogleCloudConfig {

    @Bean  // ✅ Creates a Spring-managed bean for Storage
    public Storage storage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
            new FileInputStream("C:\\Users\\rnsai\\eclipse-workspace\\Cloud_ManagementSystem\\acs-course-stuttgart-449514-2d0783dab031.json")
        );
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
