package com.kozlovskaya.springdata.lesson7.converters;

import com.kozlovskaya.springdata.lesson7.dto.ProductDto;
import com.kozlovskaya.springdata.lesson7.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
