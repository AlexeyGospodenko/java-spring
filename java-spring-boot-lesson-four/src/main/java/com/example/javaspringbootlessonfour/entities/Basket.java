package com.example.javaspringbootlessonfour.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {
    private long id = 0;
    private Map<Long, Product> productMap = new HashMap<>();

    public void add(Product product) {
        productMap.put(id++, product);
    }

    public void remove(Long id) {
        productMap.remove(id);
    }

}
