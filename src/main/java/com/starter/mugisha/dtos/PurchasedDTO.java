package com.starter.mugisha.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedDTO {
    private UUID productCode;
    private Long customerId;
    private int quantity;
    private double total;

}
