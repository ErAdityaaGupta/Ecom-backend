package com.example.ecom_backend.controller;

import com.example.ecom_backend.model.Product;
import com.example.ecom_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

//    @RequestMapping("/")
//    public String getProducts() {
//        return "List of products";
//    }

    @RequestMapping("/products")
    public List<Product> getProductsList() {
        return service.getAllProducts();
    }

}
