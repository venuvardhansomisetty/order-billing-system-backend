package com.mystore.orderbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderbillingsystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
