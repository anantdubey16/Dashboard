package com.example.dashboard.repository;

import com.example.dashboard.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepo extends JpaRepository<Product, Integer> {
}
