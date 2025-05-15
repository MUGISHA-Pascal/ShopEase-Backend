package com.starter.mugisha.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRegisterDTO {
    private String firstname;
    private String phone;
    private String email;
    private String password;

}