package com.mystore.orderbillingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderbillingsystem.entity.Invoice;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {

    List<Invoice> findByPaymentStatus(String paymentStatus);

}