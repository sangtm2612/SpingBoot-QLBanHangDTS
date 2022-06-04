package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private String fullname;
    private String phone;
    private String address;
    private List<Integer> products;
    private List<Integer> quantitys;
}
