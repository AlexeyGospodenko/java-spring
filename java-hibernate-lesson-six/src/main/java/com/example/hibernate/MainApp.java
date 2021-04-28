package com.example.hibernate;

import com.example.hibernate.configuration.AppConfig;
import com.example.hibernate.entities.Customer;
import com.example.hibernate.entities.Product;
import com.example.hibernate.services.MyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    private static SessionFactory factory;
    private static MyService myService;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        factory = context.getBean("sessionFactory", SessionFactory.class);
        myService = context.getBean("myService", MyService.class);

        prepareData();
        testService();

        factory.close();
    }

    private static void testService() {
        System.out.println("----------------");
        List<Customer> customerList = myService.getCustomersByProductId(1l);
        System.out.println("----------------");
        System.out.println(customerList.toString());
        System.out.println("----------------");
        List<Product> productList = myService.getProductsByCustomerId(1l);
        System.out.println("----------------");
        System.out.println(productList);

        //Без @ManyToMany(fetch = FetchType.EAGER) получаю
        //org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
        //com.example.hibernate.entities.Product.customers, could not initialize proxy - no Session
        //При этом если выводить полученные по id продукты/кастомеры внутри DAO классов то прекрасно работает
        //но как только я выхожу из метода, то получаю LazyInitializationException
        //Есть ли способ без использования (fetch = FetchType.EAGER)?
    }

    private static void prepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("java-hibernate-lesson-six/create-and-fill.sql")).collect(Collectors.joining("\n"));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
