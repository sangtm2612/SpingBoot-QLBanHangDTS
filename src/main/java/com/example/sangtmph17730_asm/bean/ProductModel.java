package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private String name;
    private String image;
    private Integer price;
    private Integer available;
    private Integer categoryId;
}
