package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.*;
import com.starter.mugisha.dtos.PurchasedDTO;
import com.starter.mugisha.models.Purchased;
import com.starter.mugisha.services.PurchaseService;
import com.starter.mugisha.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired private ShoppingCartService cartService;

    @PostMapping("/checkout")
    public List<PurchasedDTO> checkout(@RequestBody CheckoutDTO dto) {
        return purchaseService.checkout(dto);
    }


    @PostMapping("/cart/{email}")
    public void addToCart(@PathVariable String email, @RequestBody List<CartItemDTO> items) {
        cartService.addItemsToCart(email, items);
    }
    @GetMapping("/get-all")
    public List<Purchased> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}