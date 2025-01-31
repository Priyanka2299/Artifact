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

import java.util.ArrayList;
import java.util.List;

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
    void testValidGetProductById() throws ProductNotFoundException { //typing happy TC
        Product product = new Product(); //creating product
        product.setId(1L);
        product.setTitle("Macbook pro");
        product.setPrice(150000.0);

        when(productService.getProductById(1L)).thenReturn(product); // this is a hardcoded value

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product mockedproduct = responseEntity.getBody();
        assertEquals(product, mockedproduct);
    }
    @Test
    void testGetAllProducts(){
        List<Product> expectedProducts = new ArrayList<>();
        Product p1 = new Product(); //creating mockedProducts
        p1.setId(1L);
        p1.setTitle("iPhone 13 pro");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("iPhone 14 pro");

        Product p3 = new Product();
        p3.setId(3L);
        p3.setTitle("iPhone 15 pro");

        expectedProducts.add(p1);
        expectedProducts.add(p2);
        expectedProducts.add(p3);
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        List<Product> products = productController.getAllProducts(); //comapre id = id, title = title, etc
        if(expectedProducts.size() == products.size()){     //size of list should also be same
            for(int i=0; i<expectedProducts.size(); i++){
                assertEquals(expectedProducts.get(i), products.get(i));
            }

        }
        //assertIterableEquals(expectedProducts, products);   //iterableequals is used for comparing arraylist

    }
    @Test
    void testInvalidGetProductById(){
        //If the ProductId passed in the input doesn't exist in the db then lets say we are throwing an Exception
        //We need to write a TC to test that exception
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(100L));
    }

}
