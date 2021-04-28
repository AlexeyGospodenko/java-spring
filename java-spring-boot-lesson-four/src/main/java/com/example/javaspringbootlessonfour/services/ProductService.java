package com.example.javaspringbootlessonfour.services;

import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.repositories.ProductRepository;
import com.example.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getAllProduct(Integer pageNum, Integer PageSize) {
        Pageable paging = PageRequest.of(pageNum, PageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        return pagedResult.getContent();

    }

    @Transactional
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }

//    @Transactional
//    public List<Product> getByMinPrice (BigDecimal minPrice) {
//        //return productRepository.findProductByPriceIsGreaterThan(minPrice);
//    }

//    @Transactional
//    public List<Product> getByMaxPrice (BigDecimal maxPrice) {
//        //return productRepository.findProductByPriceIsLessThan(maxPrice);
//    }

//    @Transactional
//    public List<Product> getBetweenMinAndMaxPrice (BigDecimal minPrice, BigDecimal maxPrice) {
//        return productRepository.findProductByPriceIsBetween(minPrice, maxPrice);
//    }

    @Transactional
    public List<Product> getProductsWithFilters(String nameFilter, BigDecimal minPriceFilter, BigDecimal maxPriceFilter) {
//		if (!nameFilter.contains("%")) {
//			nameFilter = String.join("", "%", nameFilter, "%");
//		}
//		return productRepository.findProductByTitleLike(nameFilter);
        Specification<Product> specification = Specification.where(null);
        if (nameFilter != null) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter));
        }
        if (minPriceFilter != null) {
            specification = specification.and(ProductSpecification.priceMin(minPriceFilter));
        }
        if (maxPriceFilter != null) {
            specification = specification.and(ProductSpecification.priceMax(maxPriceFilter));
        }
        return productRepository.findAll(specification);
    }

}