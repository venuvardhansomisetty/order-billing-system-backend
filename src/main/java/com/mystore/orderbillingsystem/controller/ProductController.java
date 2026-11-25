package com.mystore.orderbillingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.orderbillingsystem.entity.Product;
import com.mystore.orderbillingsystem.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

   @PostMapping
@PreAuthorize("hasRole('ADMIN')")
public Product addProduct(
        @RequestBody Product product) {

    return productRepository.save(product);
}

    


    @PutMapping("/{id}")
public Product updateProduct(
        @PathVariable Long id,
        @RequestBody Product updatedProduct) {

    Product product = productRepository.findById(id)
            .orElseThrow();

    product.setName(updatedProduct.getName());
    product.setPrice(updatedProduct.getPrice());
    product.setStockQuantity(updatedProduct.getStockQuantity());

    return productRepository.save(product);
        }


        @DeleteMapping("/{id}")
public void deleteProduct(
        @PathVariable Long id) {

    productRepository.deleteById(id);

}
}