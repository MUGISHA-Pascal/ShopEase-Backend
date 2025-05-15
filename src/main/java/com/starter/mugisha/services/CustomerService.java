package com.starter.mugisha.services;

import com.starter.mugisha.models.Customer;
import com.starter.mugisha.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer registerCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }
}