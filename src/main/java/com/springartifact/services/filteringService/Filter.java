package com.springartifact.services.filteringService;

import com.springartifact.models.Product;

import java.util.List;

public interface Filter {

    List<Product> apply(List<Product> products,
                        List<String> allowedValues);
}
