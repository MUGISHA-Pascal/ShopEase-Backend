package com.starter.mugisha.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String productType;
    private double price;
    private String image;
}