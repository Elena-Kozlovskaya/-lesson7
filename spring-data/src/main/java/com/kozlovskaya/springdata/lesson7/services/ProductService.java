package com.kozlovskaya.springdata.lesson7.services;


import com.kozlovskaya.springdata.lesson7.data.Product;
import com.kozlovskaya.springdata.lesson7.exeptions.ResourceNotFoundException;
import com.kozlovskaya.springdata.lesson7.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product's cost. Product is not found, id: " + productId));
        product.setCost(product.getCost() + delta);
    }

    public List<Product> findAllByCostBetween(Integer min, Integer max) {
        if(min == 0){
            return productRepository.findAllByCostLessThanMax(max);
        } else if(max == 2000){
            return productRepository.findAllByCostMoreThanMin(min);
        } else {
            return productRepository.findAllByCostBetween(min, max);
        }
    }

    @Transactional
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
