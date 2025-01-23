package com.springartifact.controllers;

import com.springartifact.exceptions.ProductNotFoundException;
import com.springartifact.models.Product;
import com.springartifact.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductService {
    private ProductService productService;
    ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        if(product ==null){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }
//    @Override
//    public Product getProduct(Long id) {
//        return null;
//    }
    @GetMapping()
     public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //@Override
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeExceptions(){
        return null;
    }
}
