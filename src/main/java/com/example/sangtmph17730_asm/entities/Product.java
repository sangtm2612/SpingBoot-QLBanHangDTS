package com.example.sangtmph17730_asm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "available", nullable = false)
    private Integer available;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}