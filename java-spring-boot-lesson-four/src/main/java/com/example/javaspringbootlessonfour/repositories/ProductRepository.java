package com.example.javaspringbootlessonfour.repositories;

import com.example.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
        JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product>
         {
}