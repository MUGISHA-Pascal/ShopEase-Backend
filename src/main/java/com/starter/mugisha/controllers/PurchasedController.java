package com.starter.mugisha.controllers;

import com.starter.mugisha.models.*;
import com.starter.mugisha.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchases")
public class PurchasedController {
    private final PurchasedService purchasedService;
    private final ProductService productService;
    private final CustomerService customerService;

    public PurchasedController(PurchasedService purchasedService, ProductService productService, CustomerService customerService) {
        this.purchasedService = purchasedService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @PostMapping("/buy")
    public Purchased buyProduct(
            @RequestParam String email,
            @RequestParam UUID productCode,
            @RequestParam int quantity) {

        Customer customer = customerService.getCustomerByEmail(email).orElseThrow();
        Product product = productService.getProductByCode(productCode);

        return purchasedService.purchaseProduct(customer, product, quantity);
    }

    @GetMapping
    public List<Purchased> getAllPurchases() {
        return purchasedService.getAllPurchases();
    }
}
