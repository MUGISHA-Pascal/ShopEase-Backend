package com.starter.mugisha.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PurchaseRequestByEmailDTO {
    private String email;
    private UUID productCode;
    private int quantity;
}