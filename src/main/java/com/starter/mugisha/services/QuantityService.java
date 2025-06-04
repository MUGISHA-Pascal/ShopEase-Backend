package com.starter.mugisha.services;

import com.starter.mugisha.dtos.QuantityDTO;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.Quantity;
import com.starter.mugisha.repository.ProductRepository;
import com.starter.mugisha.repository.QuantityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityService {
    private final QuantityRepository quantityRepo;
    private final ProductRepository productRepository;

    public QuantityService(QuantityRepository quantityRepo, ProductRepository productRepository) {
        this.quantityRepo = quantityRepo;
        this.productRepository = productRepository;
    }
    public String addQuantity(QuantityDTO dto) {
        Product product = productRepository.findById(dto.getProductCode())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Quantity quantity = new Quantity();
        quantity.setProduct(product);
        quantity.setQuantity(dto.getQuantity());
        quantity.setOperation(dto.getOperation());


        quantityRepo.save(quantity);
        return "Quantity added successfully";
    }

    public List<Quantity> getAllQuantities() {
        return quantityRepo.findAll();
    }
}
