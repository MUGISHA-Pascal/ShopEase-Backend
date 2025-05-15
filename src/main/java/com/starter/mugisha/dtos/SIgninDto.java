package com.starter.mugisha.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SIgninDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
