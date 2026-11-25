package com.mystore.orderbillingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.orderbillingsystem.entity.Invoice;
import com.mystore.orderbillingsystem.repository.InvoiceRepository;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @PutMapping("/{id}/status/{status}")
public Invoice updatePaymentStatus(
        @PathVariable Long id,
        @PathVariable String status) {

    Invoice invoice = invoiceRepository
            .findById(id)
            .orElseThrow();

    invoice.setPaymentStatus(status);

    return invoiceRepository.save(invoice);
}

     @GetMapping("/pending")
public List<Invoice> getPendingInvoices() {

    return invoiceRepository
            .findByPaymentStatus("PENDING");
}
}