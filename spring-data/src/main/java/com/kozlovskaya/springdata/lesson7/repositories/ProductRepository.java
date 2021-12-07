package com.kozlovskaya.springdata.lesson7.repositories;

import com.kozlovskaya.springdata.lesson7.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.cost > ?1")
    List<Product> findAllByCostMoreThanMin(Integer min);

    @Query("select p from Product p where p.cost < ?1")
    List<Product> findAllByCostLessThanMax(Integer max);
}
