package com.example.javaspringbootlessonfour.services;

import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.repositories.ProductRepository;
import com.example.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Page<Product> getByParams(
                                     Optional<String> nameFilter,
                                     Optional<BigDecimal> minPrice,
                                     Optional<BigDecimal> maxPrice,
                                     Optional<Integer> pageNum,
                                     Optional<Integer> pageSize) {

        Specification<Product> specification = Specification.where(null);
        if (nameFilter.isPresent()) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter.get()));
        }

        if (minPrice.isPresent()) {
            specification = specification.and(ProductSpecification.greaterThan(minPrice.get()));
        }

        if (maxPrice.isPresent()) {
            specification = specification.and(ProductSpecification.lessThan(maxPrice.get()));
        }

        return productRepository.findAll(specification,
                PageRequest.of(pageNum.orElse(1) - 1, pageSize.orElse(4)));
    }
}