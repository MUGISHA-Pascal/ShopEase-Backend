package com.starter.mugisha.controllers;

import com.starter.mugisha.models.*;
import com.starter.mugisha.repository.UserRepository;
import com.starter.mugisha.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Shopping Cart Management", description = "APIs for managing shopping cart items")
public class ShoppingCartController {
    private UserRepository userRepository;
    private final ShoppingCartService cartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add")
    @Operation(summary = "Add item to cart", description = "Adds a product to the user's shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item added successfully"),
        @ApiResponse(responseCode = "404", description = "User or product not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ShoppingCartItem addToCart(
            @Parameter(description = "User's email address", required = true) @RequestParam String email,
            @Parameter(description = "Product code", required = true) @RequestParam UUID productCode,
            @Parameter(description = "Quantity to add", required = true) @RequestParam int quantity) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productService.getProductByCode(productCode);

        return cartService.addToCart(user, product, quantity);
    }

    @GetMapping("/get-all")
    @Operation(summary = "View cart items", description = "Retrieves all items in the user's shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved cart items"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public List<ShoppingCartItem> viewCart(
            @Parameter(description = "User's email address", required = true) @RequestParam String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartService.getCartItems(user);
    }
}