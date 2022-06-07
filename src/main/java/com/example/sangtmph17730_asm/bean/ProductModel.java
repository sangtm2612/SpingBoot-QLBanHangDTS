package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    @NotBlank
    private String name;
    private String image;
    @Min(1)
    @Max(999999)
    private Integer price;
    @NotNull
    private Integer available;
    @NotNull
    private Integer categoryId;
}
