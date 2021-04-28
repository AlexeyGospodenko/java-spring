package com.example.hibernate.repositories;

import com.example.hibernate.entities.Customer;
import com.example.hibernate.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomerDAO {
    @Autowired
    private SessionFactory factory;
    private Session session;

    public List<Customer> getCustomersByProductId(Long productId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        List<Customer> customerList = product.getCustomers();
        session.getTransaction().commit();
        return customerList;
    }
}
