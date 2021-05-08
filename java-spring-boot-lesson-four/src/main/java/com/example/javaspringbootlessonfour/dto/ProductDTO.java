package com.example.javaspringbootlessonfour.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String testParam = "test";
}