package com.starter.mugisha.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Purchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double total;
    @CreatedDate
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_code", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @PrePersist
    public void calculateTotal() {
        this.total = product.getPrice() * quantity;
    }
}