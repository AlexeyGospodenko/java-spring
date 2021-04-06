package org.example.classes;

public class Product {
    private static int classId;
    private int id;
    private String title;
    private int cost;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
        classId ++;
        id = classId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
