package com.reaulou.bvktechtest.controller;

import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product saveEmployee(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Integer getProductQuantityById(@PathVariable("id") Long id) {
        return productService.getProductQuantityById(id);
    }

    @PutMapping("/product/{id}/{quantity}")
    public Product updateProduct(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        return productService.updateProductQuantityById(id, quantity);
    }

    @PostMapping("/product/order/{id}/{quantity}")
    public Integer orderProduct(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        return productService.executeProductOrder(id, quantity);
    }

}
