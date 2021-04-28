package com.example.hibernate.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PURCHASES",
            joinColumns = @JoinColumn(name = "CUST_CUST_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROD_PROD_ID")
    )
    private List<Product> products;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
