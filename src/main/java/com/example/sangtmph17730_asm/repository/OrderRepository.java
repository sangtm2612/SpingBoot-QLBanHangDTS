package com.example.sangtmph17730_asm.repository;

import com.example.sangtmph17730_asm.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}