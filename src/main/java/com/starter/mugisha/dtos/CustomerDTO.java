package com.starter.mugisha.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private String firstname;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
}