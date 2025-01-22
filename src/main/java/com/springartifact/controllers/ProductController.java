package com.springartifact.controllers;

import com.springartifact.models.Product;
import com.springartifact.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductService {
    private ProductService productService;
    ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
//    @Override
//    public Product getProduct(Long id) {
//        return null;
//    }
    @GetMapping()
     public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

}
