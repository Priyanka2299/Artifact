package com.springartifact.services;

import com.springartifact.dtos.FakeStoreProductDto;
import com.springartifact.exceptions.ProductNotFoundException;
import com.springartifact.models.Category;
import com.springartifact.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service("FakeStoreProductService")

public class FakeStoreProductService implements ProductService {
    @Autowired
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }
    public Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDesc(dto.getCategory());
        product.setCategory(category);
        return product;

    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException(id,"Product with "+id+" not found");

        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }
    //Convert Dto into Product object
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto [] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);//used arrays instead of List because of Type Erasion

        //convert List of FakeStoreProductDTO to List of Products
        ArrayList<Product> response = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            response.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return response;
    }
    @Override
    public Product replaceProduct(Long id, Product product) { //in the request I will be sending fakestorproductdto object instead of product object
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConvertorExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConvertorExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConvertors());
        FakeStoreProductDto response =
                restTemplate.execute( "https://fakestoreapi.com/products/" +id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(response);
    }


}
