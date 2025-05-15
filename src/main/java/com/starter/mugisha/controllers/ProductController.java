package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.ProductDto;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.services.ProductService;
import com.starter.mugisha.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }



    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{code}")
    public Product getByCode(@PathVariable UUID code) {
        return productService.getProductByCode(code);
    }
}