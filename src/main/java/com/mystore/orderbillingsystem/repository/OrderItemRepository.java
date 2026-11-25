package com.mystore.orderbillingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderbillingsystem.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

}