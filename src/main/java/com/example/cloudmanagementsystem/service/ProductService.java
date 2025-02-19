package com.example.cloudmanagementsystem.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ProductService {

    private static final String BUCKET_NAME = "product_db";
    private static final String OBJECT_NAME = "products.json"; // Name of the JSON file in GCS
    private final Storage storage;

    public ProductService() throws IOException {
        this.storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build()
                .getService();
    }

    public void uploadJsonToGCS(String productName, int quantity) throws IOException {
        // Convert data to JSON format
        Map<String, Object> productData = new HashMap<>();
        productData.put("productName", productName);
        productData.put("quantity", quantity);

        // Convert object to JSON string
        String jsonData = new ObjectMapper().writeValueAsString(productData);

        // Upload JSON data as a Blob (text file) to Google Cloud Storage
        BlobId blobId = BlobId.of(BUCKET_NAME, OBJECT_NAME);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/json").build();
        storage.create(blobInfo, jsonData.getBytes(StandardCharsets.UTF_8));

        System.out.println("JSON data uploaded to GCS successfully!");
    }

    public String getProductsFromGCS() throws IOException {
        Blob blob = storage.get(BlobId.of(BUCKET_NAME, OBJECT_NAME));

        if (blob == null) {
            return "No products found in GCS.";
        }

        return new String(blob.getContent(), StandardCharsets.UTF_8);
    }
}
