package com.springartifact.dtos.search;

import com.springartifact.dtos.FakeStoreProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
@Getter
@Setter
public class SearchResponseDto {
//    private List<FakeStoreProductDto> products;
//    private int pageNumber;
//    private boolean lastPage;
    private Page<FakeStoreProductDto> productsPage;
}
