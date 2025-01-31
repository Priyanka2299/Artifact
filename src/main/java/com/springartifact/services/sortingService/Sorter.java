package com.springartifact.services.sortingService;

import com.springartifact.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);

}
