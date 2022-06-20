package com.example.sangtmph17730_asm.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "unit", nullable = false)
    private Integer unit;

    @Column(name = "min_order_value", nullable = false)
    private Integer minOrderValue;

    @Column(name = "max_discount", nullable = false)
    private Integer maxDiscount;

    @Column(name = "available", nullable = false)
    private Integer available;

}