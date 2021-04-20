package com.example.javaspringbootlessonfour.services;

import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductDAO productRepository;

    @Autowired
    public void setProductRepository(ProductDAO productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public void add(Product product) {
        productRepository.add(product);
    }

    public void update(Product product) {
        productRepository.saveOrUpdate(product);
    }
}