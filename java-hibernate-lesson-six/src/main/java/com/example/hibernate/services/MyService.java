package com.example.hibernate.services;

import com.example.hibernate.entities.Customer;
import com.example.hibernate.entities.Product;
import com.example.hibernate.repositories.CustomerDAO;
import com.example.hibernate.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyService {
    private ProductDAO productRepository;
    private CustomerDAO customerRepository;

    @Autowired
    public void setProductRepository(ProductDAO productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerDAO customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Product> getProductsByCustomerId (Long productId) {
        return productRepository.getProductsByCustomerId(productId);
    }

    public List<Customer>  getCustomersByProductId (Long customerId) {
        return customerRepository.getCustomersByProductId(customerId);
    }

}

