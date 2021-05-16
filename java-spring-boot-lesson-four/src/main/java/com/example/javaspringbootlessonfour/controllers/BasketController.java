package com.example.javaspringbootlessonfour.controllers;

import com.example.javaspringbootlessonfour.entities.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private Basket basket;

    @Autowired
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @GetMapping
    public String indexBasketPage(Model model) {
        model.addAttribute("products", basket.getProductMap());
        return "basket_views/index";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        basket.remove(id);
        return "redirect:/basket";
    }
}
