package com.springartifact.services;

import com.springartifact.dtos.FakeStoreProductDto;
import com.springartifact.models.Category;
import com.springartifact.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service("FakeStoreProductService")

public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
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
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }
    //Convert Dto into Product object

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
