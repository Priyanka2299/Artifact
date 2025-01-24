package com.springartifact.controllers;

import com.springartifact.exceptions.ProductNotFoundException;
import com.springartifact.models.Product;
import com.springartifact.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest         //to make this callss capable to use framework we will add @springboottest since autowired is coming from springframework
@SuppressWarnings("deprecation")
class ProductControllerTest {
    @Autowired          //autowired to inject dependency since we donot want to create an object here we will be executing it
    private ProductController productController; // we need an object of productcontroller
    @MockBean      //this is going to be mocked object. Independent of ProductService layer
    private ProductService productService;

    ProductControllerTest() throws ProductNotFoundException {
    }

    @Test
    void validGetProductByIdTest() throws ProductNotFoundException { //typing happy TC
        Product product = new Product(); //creating product
        product.setId(1L);
        product.setTitle("Macbook pro");
        product.setPrice(150000.0);

        when(productService.getProductById(1L)).thenReturn(product); // this is a hardcoded value

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product mockedproduct = responseEntity.getBody();
        assertEquals(product, mockedproduct);
    }
}