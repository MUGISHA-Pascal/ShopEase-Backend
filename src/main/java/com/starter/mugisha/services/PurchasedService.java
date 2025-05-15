package com.starter.mugisha.services;

import com.starter.mugisha.models.Customer;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.Purchased;
import com.starter.mugisha.repository.PurchasedRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchasedService {
    private final PurchasedRepository purchasedRepo;

    public PurchasedService(PurchasedRepository purchasedRepo) {
        this.purchasedRepo = purchasedRepo;
    }

    public Purchased purchaseProduct(Customer customer, Product product, int quantity) {
        Purchased purchased = new Purchased();
        purchased.setCustomer(customer);
        purchased.setProduct(product);
        purchased.setQuantity(quantity);
        purchased.setDate(LocalDate.now());
        // total is calculated via DB trigger
        return purchasedRepo.save(purchased);
    }

    public List<Purchased> getAllPurchases() {
        return purchasedRepo.findAll();
    }
}