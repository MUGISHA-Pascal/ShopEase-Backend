package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.*;
import com.starter.mugisha.dtos.PurchasedDTO;
import com.starter.mugisha.models.Purchased;
import com.starter.mugisha.services.PurchaseService;
import com.starter.mugisha.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Purchase Management", description = "APIs for managing purchases and checkout")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired 
    private ShoppingCartService cartService;

    @PostMapping("/checkout")
    @Operation(summary = "Process checkout", description = "Processes the checkout for items in the cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Checkout processed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid checkout data"),
        @ApiResponse(responseCode = "404", description = "Cart items not found")
    })
    public List<PurchasedDTO> checkout(@RequestBody CheckoutDTO dto) {
        return purchaseService.checkout(dto);
    }

    @PostMapping("/cart/{email}")
    @Operation(summary = "Add items to cart", description = "Adds multiple items to the user's shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items added successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public void addToCart(
            @Parameter(description = "User's email address", required = true) @PathVariable String email, 
            @RequestBody List<CartItemDTO> items) {
        cartService.addItemsToCart(email, items);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all purchases", description = "Retrieves a list of all purchases in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all purchases"),
        @ApiResponse(responseCode = "404", description = "No purchases found")
    })
    public List<Purchased> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}