package com.example.javaspringbootlessonfour.repositories.specifications;

import com.example.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> titleLike(String titleFilter) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
    }

    public static Specification<Product> priceMin(BigDecimal minPriceFilter) {
        return (root, query, builder) -> builder.greaterThan(root.get("price"), minPriceFilter);
    }

    public static Specification<Product> priceMax(BigDecimal maxPriceFilter) {
        return (root, query, builder) -> builder.lessThan(root.get("price"), maxPriceFilter);
    }

}
