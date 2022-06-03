package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    private Integer activated;
    private Integer admin;
}
