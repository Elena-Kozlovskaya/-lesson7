package com.kozlovskaya.springdata.lesson7.repositories;

import com.kozlovskaya.springdata.lesson7.data.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productNews;

    @PostConstruct
    public void init() {
        this.productNews = new ArrayList<>(List.of(
                new Product(1L, "Potato", 2),
                new Product(2L, "Carrot", 4),
                new Product(3L, "Pepper", 8),
                new Product(4L, "Tomato", 16)
        ));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(productNews);
    }

    public void deleteById(Long id){
        productNews.removeIf(p-> p.getId().equals(id));
    }

    public Product findProductById(Long id) {
        return productNews.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();
    }
}
