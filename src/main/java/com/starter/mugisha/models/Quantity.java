package com.starter.mugisha.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private String operation;
    @CreatedDate
    private LocalDate date;

    @JsonBackReference(value = "product-quantities")
    @ManyToOne
    @JoinColumn(name = "product_code", nullable = false)
    private Product product;

}