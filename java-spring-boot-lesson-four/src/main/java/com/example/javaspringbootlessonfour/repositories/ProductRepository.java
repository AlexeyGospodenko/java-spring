package com.example.javaspringbootlessonfour.repositories;

import com.example.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
        JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product>
         {
//    List<Product> findProductByTitleLike(String title);
//    List<Product> findProductByPriceIsGreaterThan(BigDecimal minPrice);
//    List<Product> findProductByPriceIsLessThan(BigDecimal maxPrice);
//    List<Product> findProductByPriceIsBetween(BigDecimal minPrice, BigDecimal maxPrice);

}