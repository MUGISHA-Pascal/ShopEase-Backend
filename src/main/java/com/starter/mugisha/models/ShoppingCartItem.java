package com.starter.mugisha.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "user-cart")
    @ManyToOne
    private User user;

    @JsonBackReference(value = "product-cart")
    @ManyToOne
    private Product product;

    private int quantity;
    private LocalDate addedDate = LocalDate.now();
}