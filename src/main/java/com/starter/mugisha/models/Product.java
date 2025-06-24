package com.starter.mugisha.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.starter.mugisha.dtos.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class eProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID code;

    private String name;
    private String productType;
    private double price;
    @CreatedDate
    private LocalDate inDate;
    private String image;

    @JsonManagedReference(value = "product-quantities")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Quantity> quantities;

    @JsonManagedReference(value = "product-purchases")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Purchased> purchases;

    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.productType = productDto.getProductType();
        this.image=productDto.getImage();
    }
}
