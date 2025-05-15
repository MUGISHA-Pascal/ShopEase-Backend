package com.starter.mugisha.controllers;

import com.starter.mugisha.models.*;
import com.starter.mugisha.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final ProductService productService;
    private final CustomerService customerService;

    public ShoppingCartController(ShoppingCartService cartService, ProductService productService, CustomerService customerService) {
        this.cartService = cartService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ShoppingCartItem addToCart(
            @RequestParam String email,
            @RequestParam UUID productCode,
            @RequestParam int quantity) {

        Customer customer = customerService.getCustomerByEmail(email).orElseThrow();
        Product product = productService.getProductByCode(productCode);

        return cartService.addToCart(customer, product, quantity);
    }

    @GetMapping
    public List<ShoppingCartItem> viewCart(@RequestParam String email) {
        Customer customer = customerService.getCustomerByEmail(email).orElseThrow();
        return cartService.getCartItems(customer);
    }

    @PostMapping("/checkout")
    public void checkout(@RequestParam String email) {
        Customer customer = customerService.getCustomerByEmail(email).orElseThrow();
        cartService.checkout(customer);
    }
}