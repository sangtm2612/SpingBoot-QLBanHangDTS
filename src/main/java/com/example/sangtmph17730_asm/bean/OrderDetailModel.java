package com.example.sangtmph17730_asm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel {
    private String fullname;
    private String phone;
    private String address;
    private int quantity;
    private List<Integer> products;
    private List<Integer> quantitys;
}
