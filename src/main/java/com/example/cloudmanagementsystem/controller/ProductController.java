package com.example.cloudmanagementsystem.controller;

import com.example.cloudmanagementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/upload")
    public String uploadProductToGCS(@RequestBody Map<String, Object> productData) {
        try {
            String productName = productData.get("productName").toString();
            int quantity = (int) productData.get("quantity");
            productService.uploadJsonToGCS(productName, quantity);
            return "Product uploaded to GCS successfully!";
        } catch (IOException e) {
            return "Error uploading product: " + e.getMessage();
        }
    }

    @GetMapping
    public String getProducts() {
        try {
            return productService.getProductsFromGCS();
        } catch (IOException e) {
            return "Error retrieving products: " + e.getMessage();
        }
    }
}
