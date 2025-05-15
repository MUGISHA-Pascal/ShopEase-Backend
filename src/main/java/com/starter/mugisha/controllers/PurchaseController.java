package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.*;
import com.starter.mugisha.dtos.PurchasedDTO;
import com.starter.mugisha.services.PurchaseService;
import com.starter.mugisha.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService service;
    @Autowired private ShoppingCartService cartService;

    @PostMapping("/checkout")
    public List<PurchasedDTO> checkout(@RequestBody CheckoutDTO dto) {
        return service.checkout(dto);
    }

    @PostMapping("/by-email")
    public PurchasedDTO purchaseByEmail(@RequestBody PurchaseRequestByEmailDTO dto) {
        return service.purchaseByEmail(dto);
    }

    @PostMapping("/cart/{email}")
    public void addToCart(@PathVariable String email, @RequestBody List<CartItemDTO> items) {
        cartService.addItemsToCart(email, items);
    }
}