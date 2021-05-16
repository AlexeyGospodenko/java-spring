package com.example.javaspringbootlessonfour.rest;

import com.example.javaspringbootlessonfour.dto.ProductDTO;
import com.example.javaspringbootlessonfour.entities.Product;
import com.example.javaspringbootlessonfour.services.exceptions.NotFoundException;
import com.example.javaspringbootlessonfour.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "API to manipulate product resources")
@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public ProductDTO findById(@PathVariable("id") Long id) {
        //return service.findById(id).orElseThrow(NotFoundException::new);
        return service.findByIdDTO(id);
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<ProductDTO> findAll() {
        //return service.findAll();
        return service.findAllDTO();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createUser(@RequestBody Product product) {
        service.createOrUpdate(product);
        return product;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateUser(@RequestBody Product product) {
        service.createOrUpdate(product);
        return product;
    }

    @DeleteMapping("/{id}/id")
    public void deleteById(@PathVariable("id") Long id) {
        service.remove(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
