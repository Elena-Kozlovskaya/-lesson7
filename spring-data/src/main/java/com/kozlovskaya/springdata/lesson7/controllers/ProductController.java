package com.kozlovskaya.springdata.lesson7.controllers;


import com.kozlovskaya.springdata.lesson7.data.Product;
import com.kozlovskaya.springdata.lesson7.exeptions.ResourceNotFoundException;
import com.kozlovskaya.springdata.lesson7.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }

    @GetMapping("/products/cost_between")
    public List<Product> findProductByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "2000")Integer max) {
        System.out.println(min + " " + max);
        return productService.findAllByCostBetween(min, max);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }



}
