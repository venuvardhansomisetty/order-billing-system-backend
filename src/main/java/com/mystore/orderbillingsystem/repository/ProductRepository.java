package com.mystore.orderbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderbillingsystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}