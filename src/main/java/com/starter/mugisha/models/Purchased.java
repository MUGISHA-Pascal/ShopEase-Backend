package com.starter.mugisha.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference(value = "product-purchases")
    @ManyToOne
    @JoinColumn(name = "product_code", nullable = false)
    private Product product;

    @JsonBackReference(value = "user-purchases")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void calculateTotal() {
        this.total = product.getPrice() * quantity;
    }
}