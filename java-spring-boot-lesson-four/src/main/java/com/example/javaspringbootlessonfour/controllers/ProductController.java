package com.example.javaspringbootlessonfour.controllers;

import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model,
                            @RequestParam(name = "titleFilter", required = false) String titleFilter,
                            @RequestParam(name = "minPrice", required = false) BigDecimal minPriceFilter,
                            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPriceFilter,
                            @RequestParam(defaultValue = "0") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize) {
        if (titleFilter != null || minPriceFilter != null || maxPriceFilter != null) {
           model.addAttribute("products", productService.getProductsWithFilters(titleFilter, minPriceFilter, maxPriceFilter));
        } else {
            model.addAttribute("products", productService.getAllProduct(pageNum, pageSize));
        }
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productService.addOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_views/product_form";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.remove(id);
        return "redirect:/product";
    }
}
