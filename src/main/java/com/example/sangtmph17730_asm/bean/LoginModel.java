package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
