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
    public List<Product> findProductByScoreBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "200") Integer max) {
        return productService.findAllByCostBetween(min, max);
    }
    @GetMapping("/products/cost_moreThanMin")
    public List<Product> findByCostMoreThanMin(@RequestParam(defaultValue = "0") Integer min) {
        return productService.findAllByCostMoreThanMin(min);
    }
    @GetMapping("/products/cost_lessThanMax")
    public List<Product> findByCostLessThanMax(@RequestParam(required = false) Integer max) {
        return productService.findAllByCostLessThanMax(max);
    }

    @PostMapping("/add_product")
    public void addProduct(@RequestBody Product p){
        productService.addProduct(p);
        System.out.println(p.getTitle());
    }



}
