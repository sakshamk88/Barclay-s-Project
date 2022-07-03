package com.barclays.store.controller;

import com.barclays.store.entity.Product;
import com.barclays.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping(value = "/addProducts")
    public void addProducts(@RequestBody List<Product> products){
        products.forEach(product -> {
            productRepository.save(product);
        });
    }

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
    }
}
