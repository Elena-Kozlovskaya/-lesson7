package com.kozlovskaya.springdata.lesson7.controllers;


import com.kozlovskaya.springdata.lesson7.dto.ProductDto;
import com.kozlovskaya.springdata.lesson7.entities.Product;
import com.kozlovskaya.springdata.lesson7.exeptions.ResourceNotFoundException;
import com.kozlovskaya.springdata.lesson7.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer min,
            @RequestParam(name = "max_cost", required = false) Integer max,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if(page < 1) {
            page = 1;
        }

        return productService.find (min, max, titlePart, page).map(
                p -> new ProductDto(p)
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    /*@PostMapping
    public Product addProduct(@RequestBody Product product){
        product.setId(null);
        return productService.addProduct(product);
    }*/

    @PostMapping
    public Product addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    /*@GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }*/

    /*@GetMapping("/products/cost_between")
    public List<Product> findProductByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(required = false)Integer max) {
        System.out.println(min + " " + max);
        return productService.findAllByCostBetween(min, max);
    }*/





}
