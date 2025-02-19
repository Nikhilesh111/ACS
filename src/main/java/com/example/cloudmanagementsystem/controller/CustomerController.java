package com.example.cloudmanagementsystem.controller;

import com.example.cloudmanagementsystem.entity.Customer;
import com.example.cloudmanagementsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")  // Base URL for this controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Show customers page
    @GetMapping
    public String showCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println("✅ Customers List Fetched: " + customers); // Debug log
        model.addAttribute("customers", customers);
        return "customers"; // Must match "customers.html" in templates
    }

    // ✅ Add a new customer
    @PostMapping("/add")
    public String addCustomer(@RequestParam String name,
                              @RequestParam String productName,
                              @RequestParam int quantity) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setProductName(productName);
        customer.setQuantity(quantity);
        customerService.addCustomer(customer);
        return "redirect:/customers";  // Refresh page after adding
    }
}
