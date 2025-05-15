package com.starter.mugisha.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CheckoutDTO {
    private String customerEmail; // changed from customerId
    private List<CartItemDTO> items;
}