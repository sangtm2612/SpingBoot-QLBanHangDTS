package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fullname;
    @NotBlank
    @Email
    private String email;
    private String photo;
    @NotNull
    private Integer activated;
    @NotNull
    private Integer admin;
}
