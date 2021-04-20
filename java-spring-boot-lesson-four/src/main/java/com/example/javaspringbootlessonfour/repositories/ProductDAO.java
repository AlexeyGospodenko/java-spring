package com.example.javaspringbootlessonfour.repositories;

import com.example.javaspringbootlessonfour.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDAO {

    private SessionFactory factory;
    private Session session;

    @PostConstruct
    public void init() {

        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        add(new Product("MacBook", "Ultra low and Great Power", new BigDecimal(3000)));
        add(new Product("iPhone", "The most expensive phone by credit", new BigDecimal(1000)));
        add(new Product("iPad", "More size - more cost", new BigDecimal(2000)));
    }

    public void add(Product product) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    public void saveOrUpdate(Product product) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product updatedProduct = session.get(Product.class, product.getId());
        //Вот тут перечисление сетов не элегантно как-то
        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setDescription(product.getDescription());
        session.getTransaction().commit();
    }

    public void deleteById(long id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
    }

    public Product findById(long id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    public List<Product> findAll() {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Product> productList = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return productList;
    }


}
