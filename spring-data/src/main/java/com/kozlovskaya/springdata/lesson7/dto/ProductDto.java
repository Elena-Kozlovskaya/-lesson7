package com.kozlovskaya.springdata.lesson7.dto;

import com.kozlovskaya.springdata.lesson7.entities.Product;

import javax.persistence.*;

public class ProductDto {

    private Long id;

    private String title;

    private Integer cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public ProductDto() {
    }

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

}