package com.mystore.orderbillingsystem.service;

public class InvoiceService {

    public Double calculateTotal(Double price, Integer quantity) {
        return price * quantity;
    }
}