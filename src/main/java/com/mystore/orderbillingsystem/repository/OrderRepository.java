package com.mystore.orderbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderbillingsystem.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}