package com.mystore.orderbillingsystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.orderbillingsystem.dto.OrderItemRequest;
import com.mystore.orderbillingsystem.dto.OrderRequest;
import com.mystore.orderbillingsystem.entity.Customer;
import com.mystore.orderbillingsystem.entity.Invoice;
import com.mystore.orderbillingsystem.entity.Order;
import com.mystore.orderbillingsystem.entity.OrderItem;
import com.mystore.orderbillingsystem.entity.Product;
import com.mystore.orderbillingsystem.repository.CustomerRepository;
import com.mystore.orderbillingsystem.repository.InvoiceRepository;
import com.mystore.orderbillingsystem.repository.OrderItemRepository;
import com.mystore.orderbillingsystem.repository.OrderRepository;
import com.mystore.orderbillingsystem.repository.ProductRepository;


@Service
public class PlaceOrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;


  public Order placeOrder(OrderRequest request) {

     Customer customer = customerRepository
        .findById(request.getCustomerId())
        .orElseThrow();


    Order order = new Order();

    order.setOrderDate(LocalDate.now());
    order.setStatus("PENDING");

   


order.setCustomer(customer);



    order = orderRepository.save(order);

    Double totalAmount = 0.0;

    for (OrderItemRequest itemRequest : request.getItems()) {

        Product product = productRepository
                .findById(itemRequest.getProductId())
                .orElseThrow();

        if (product.getStockQuantity() < itemRequest.getQuantity()) {

            throw new RuntimeException(
                    "Not enough stock for product: "
                            + product.getName());
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(itemRequest.getQuantity());

        orderItemRepository.save(orderItem);

        totalAmount += product.getPrice()
                * itemRequest.getQuantity();

        product.setStockQuantity(
                product.getStockQuantity()
                        - itemRequest.getQuantity());

        productRepository.save(product);
    }

    Invoice invoice = new Invoice();

    invoice.setOrder(order);
    invoice.setTotalAmount(totalAmount);
    invoice.setPaymentStatus("PENDING");

    

    invoiceRepository.save(invoice);

    return order;

    
}


}

