package com.example.hibernate.repositories;

import com.example.hibernate.entities.Customer;
import com.example.hibernate.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDAO {
    @Autowired
    private SessionFactory factory;
    private Session session;

    public List<Product> getProductsByCustomerId(Long customerId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        List<Product> productList = customer.getProducts();
        session.getTransaction().commit();
        return productList;
    }
}