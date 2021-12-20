package com.kozlovskaya.springdata.lesson7;

import com.kozlovskaya.springdata.lesson7.converters.ProductConverter;
import com.kozlovskaya.springdata.lesson7.dto.ProductDto;
import com.kozlovskaya.springdata.lesson7.exeptions.ResourceNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {

    private final ProductConverter productConverter;
    private List<ProductDto> cartsProductDtos = new ArrayList<>();

    public ProductDto add(ProductDto productDto) {
        cartsProductDtos.add(productDto);
        return productDto;
    }

    public void delete(Long id) {
        cartsProductDtos.remove(
                cartsProductDtos
                        .stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst().orElseThrow(() -> new ResourceNotFoundException("product is not found in cart")));
    }

    public List<ProductDto> getCart() {
        return Collections.unmodifiableList(cartsProductDtos);
    }


}
