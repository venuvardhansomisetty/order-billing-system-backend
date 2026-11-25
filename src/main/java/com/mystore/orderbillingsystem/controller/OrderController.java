package com.mystore.orderbillingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.orderbillingsystem.dto.OrderRequest;
import com.mystore.orderbillingsystem.entity.Order;
import com.mystore.orderbillingsystem.repository.OrderRepository;
import com.mystore.orderbillingsystem.service.PlaceOrderService;




@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }


    @Autowired
    private PlaceOrderService placeOrderService;


    @PostMapping("/place-order")
    public Order placeOrder(
        @RequestBody OrderRequest request) {

    return placeOrderService.placeOrder(request);
}

@GetMapping("/{id}")
public Order getOrderById(
        @PathVariable Long id) {

    return orderRepository
            .findById(id)
            .orElseThrow();
}
}