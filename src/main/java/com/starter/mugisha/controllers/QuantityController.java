package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.QuantityDTO;
import com.starter.mugisha.models.Quantity;
import com.starter.mugisha.services.QuantityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantities")
public class QuantityController {
    private final QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuantity(@RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(quantityService.addQuantity(dto));
    }

    @GetMapping("/get-all")
    public List<Quantity> getAll() {
        return quantityService.getAllQuantities();
    }
}
