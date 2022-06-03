package com.example.sangtmph17730_asm.repository;

import com.example.sangtmph17730_asm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}