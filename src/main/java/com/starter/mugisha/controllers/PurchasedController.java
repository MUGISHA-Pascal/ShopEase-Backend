package com.starter.mugisha.controllers;

import com.starter.mugisha.models.*;
import com.starter.mugisha.repository.UserRepository;
import com.starter.mugisha.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchases")
public class PurchasedController {
    private final PurchasedService purchasedService;
    private final ProductService productService;
    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    public PurchasedController(PurchasedService purchasedService, ProductService productService, UserService userService) {
        this.purchasedService = purchasedService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/buy")
    public Purchased buyProduct(
            @RequestParam String email,
            @RequestParam UUID productCode,
            @RequestParam int quantity) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productService.getProductByCode(productCode);

        return purchasedService.purchaseProduct(user, product, quantity);
    }

    @GetMapping
    public List<Purchased> getAllPurchases() {
        return purchasedService.getAllPurchases();
    }
}
