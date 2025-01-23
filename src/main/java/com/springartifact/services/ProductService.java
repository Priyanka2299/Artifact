package com.springartifact.services;

import com.springartifact.exceptions.ProductNotFoundException;
import com.springartifact.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);

}
