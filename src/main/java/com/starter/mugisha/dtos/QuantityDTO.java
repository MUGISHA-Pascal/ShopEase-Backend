package com.starter.mugisha.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class QuantityDTO {
    private UUID productCode;
    private int quantity;
    private String operation;

}