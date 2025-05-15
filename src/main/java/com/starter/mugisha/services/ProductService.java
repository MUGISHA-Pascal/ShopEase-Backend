package com.starter.mugisha.services;

import com.starter.mugisha.dtos.ProductDto;
import com.starter.mugisha.exceptions.ApiRequestException;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.repository.ProductRepository;
import com.starter.mugisha.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(ProductDto product) {
        Product product1 = new Product(product);
        return productRepo.save(product1);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductByCode(UUID code) {
        return productRepo.findById(code).orElse(null);
    }
}