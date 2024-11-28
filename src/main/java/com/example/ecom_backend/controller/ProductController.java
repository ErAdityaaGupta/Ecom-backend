package com.example.ecom_backend.controller;

import com.example.ecom_backend.model.Product;
import com.example.ecom_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){

        try{
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);

        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

}
