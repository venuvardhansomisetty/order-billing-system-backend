package com.mystore.orderbillingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.orderbillingsystem.entity.Product;
import com.mystore.orderbillingsystem.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product updateStock(Long productId, Integer quantityOrdered) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantityOrdered) {
            throw new RuntimeException("Out of Stock");
        }

        product.setStockQuantity(
                product.getStockQuantity() - quantityOrdered
        );

        return productRepository.save(product);
    }
}