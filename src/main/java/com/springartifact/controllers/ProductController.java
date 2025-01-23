package com.springartifact.controllers;

import com.springartifact.models.Product;
import com.springartifact.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
        return productService.getAllProducts();
    }

    //@Override
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

}
