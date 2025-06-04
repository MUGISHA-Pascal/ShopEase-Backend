package com.starter.mugisha.controllers;

import com.starter.mugisha.models.*;
import com.starter.mugisha.repository.UserRepository;
import com.starter.mugisha.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private UserRepository userRepository;
    private final ShoppingCartService cartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ShoppingCartItem addToCart(
            @RequestParam String email,
            @RequestParam UUID productCode,
            @RequestParam int quantity) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productService.getProductByCode(productCode);

        return cartService.addToCart(user, product, quantity);
    }

    @GetMapping("/get-all")
    public List<ShoppingCartItem> viewCart(@RequestParam String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartService.getCartItems(user);
    }

}