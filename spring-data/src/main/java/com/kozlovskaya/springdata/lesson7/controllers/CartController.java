package com.kozlovskaya.springdata.lesson7.controllers;


import com.kozlovskaya.springdata.lesson7.Cart;
import com.kozlovskaya.springdata.lesson7.converters.ProductConverter;
import com.kozlovskaya.springdata.lesson7.dto.ProductDto;
import com.kozlovskaya.springdata.lesson7.entities.Product;
import com.kozlovskaya.springdata.lesson7.exeptions.ResourceNotFoundException;
import com.kozlovskaya.springdata.lesson7.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final Cart cart;

    @GetMapping
    public List<ProductDto> getCartList() {
        return cart.getCart();
    }

    @GetMapping("/{id}")
    public ProductDto addProduct(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product is not found"));
        ProductDto productDto = productConverter.entityToDto(product);
        return cart.add(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cart.delete(id);
    }
}
