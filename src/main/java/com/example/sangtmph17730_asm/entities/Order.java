package com.example.sangtmph17730_asm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @Column(name = "create_date", nullable = false)
    private Date createDate;


    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "status", nullable = true)
    private Integer status;

}