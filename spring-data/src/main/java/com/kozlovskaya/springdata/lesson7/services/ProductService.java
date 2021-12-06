package com.kozlovskaya.springdata.lesson7.services;


import com.kozlovskaya.springdata.lesson7.data.Product;
import com.kozlovskaya.springdata.lesson7.repositories.ProductDaoWeb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDaoWeb productDaoWeb;


    public ProductService(ProductDaoWeb productDaoWeb) {
        this.productDaoWeb = productDaoWeb;
    }


    public List<Product> getAllProducts() {
        return productDaoWeb.findAll();
    }

    public void deleteById(Long id) {
        productDaoWeb.deleteById(id);
    }

    public void changeCost(Long id, Integer delta) {
        Product product = productDaoWeb.findById(id);
        product.setCost(product.getCost() + delta);
        productDaoWeb.saveOrUpdate(product);
    }
}
