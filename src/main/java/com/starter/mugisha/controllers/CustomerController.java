package com.starter.mugisha.controllers;

import com.starter.mugisha.models.Customer;
import com.starter.mugisha.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    @GetMapping("/by-email")
    public Customer getByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email).orElse(null);
    }
}