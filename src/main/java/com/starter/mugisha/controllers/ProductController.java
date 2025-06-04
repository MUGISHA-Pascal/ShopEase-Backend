package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.ProductDto;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.services.ProductService;
import com.starter.mugisha.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new product", description = "Creates a new product in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public Product addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all products", description = "Retrieves a list of all products in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all products"),
        @ApiResponse(responseCode = "404", description = "No products found")
    })
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{code}")
    @Operation(summary = "Get product by code", description = "Retrieves a specific product by its unique code")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public Product getByCode(@Parameter(description = "Product code", required = true) @PathVariable UUID code) {
        return productService.getProductByCode(code);
    }
}