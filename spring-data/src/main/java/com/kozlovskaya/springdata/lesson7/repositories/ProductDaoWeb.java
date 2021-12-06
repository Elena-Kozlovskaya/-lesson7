package com.kozlovskaya.springdata.lesson7.repositories;

import com.kozlovskaya.springdata.lesson7.data.Product;

import java.util.List;

public interface ProductDaoWeb {
    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    void saveOrUpdate(Product product);


}
