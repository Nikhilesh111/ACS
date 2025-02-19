package com.example.cloudmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")  // Ensure this matches your database table name
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private int quantity;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String string) { this.id = string; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
