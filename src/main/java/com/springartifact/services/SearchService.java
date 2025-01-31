package com.springartifact.services;

import com.springartifact.dtos.search.FilterDto;
import com.springartifact.dtos.search.SortingCriteria;
import com.springartifact.models.Product;
import com.springartifact.repositories.ProductRepository;
import com.springartifact.services.filteringService.Filter;
import com.springartifact.services.filteringService.FilterFactory;
import com.springartifact.services.sortingService.SorterFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(
            String query,
            List<FilterDto> filters,
            SortingCriteria sortingCriteria,
            int pageNumber,
            int pageSize) { // 5    // 5 -> (5 * (pageNumber - 1)) -> (pageNumber * pageSize) - 1
                                    //                           [5 -> 9]
        List<Product> products = productRepository.findByTitleContaining(String query);
        for(FilterDto filter : filters){
            products = FilterFactory.getFilterFromKey(
                    FilterDto.getKey()
            ).apply(products, FilterDto.getValues());
        }
        products = SorterFactory.getSorterByCriteria(sortingCriteria).sort(products);

        List<Product> productsOnPage = new ArrayList<>();

        for (int i = pageSize * (pageNumber - 1); i <= (pageSize * pageNumber) - 1; ++i) {
            productsOnPage.add(products.get(i));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
        return new PageImpl<>(productsOnPage, pageable, products.size());
    }
    public Page<Product> simpleSearch(
            String query,
            Long categoryId,
            int pageNumber,
            int pageSize,
            String sortingAttribute
    ) {
        // select * from products
        // where title like "%query%"
        // and categoryID = {categoryId}
        // limit {pageSize} offset pageNumbver * pageSize
        // orderBy sortingAttribute;
        Page<Product> products = productRepository
                .findAllByTitleContainingAndCategory_Id(
                        query, categoryId,
                        PageRequest.of(
                                pageNumber,
                                pageSize,
                                Sort.by(sortingAttribute).descending()));

        return products;
    }

}
