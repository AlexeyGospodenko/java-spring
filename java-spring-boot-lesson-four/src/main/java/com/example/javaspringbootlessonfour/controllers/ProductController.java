package com.example.javaspringbootlessonfour.controllers;

import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.services.NotFoundException;
import com.example.javaspringbootlessonfour.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String indexPage(Model model,
                            @RequestParam(name = "titleFilter", required = false) Optional<String> titleFilter,
                            @RequestParam(name = "minPrice", required = false) Optional<BigDecimal> minPrice,
                            @RequestParam(name = "maxPrice", required = false) Optional<BigDecimal> maxPrice,
                            @RequestParam(name = "pageNum", required = false) Optional<Integer> pageNum,
                            @RequestParam(name = "pageSize", required = false) Optional<Integer> pageSize) {

        model.addAttribute("products", productService.getByParams(titleFilter, minPrice, maxPrice, pageNum, pageSize));
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getById(id).orElseThrow(NotFoundException::new));
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

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}