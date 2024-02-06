package com.reaulou.bvktechtest.controller;

import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.service.MessageBuildService;
import com.reaulou.bvktechtest.service.MessageParseService;
import com.reaulou.bvktechtest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private MessageParseService messageParseService;
    private MessageBuildService messageBuilderService;


    @RequestMapping("/product/add")
    public ResponseEntity saveEmployee(RequestEntity<String> request) {
        Product responseProduct = null;

        // parse request
        HttpMethod method = request.getMethod();
        String body = request.getBody();

        Product requestProduct = messageParseService.parseProduct();

        // service
        responseProduct = productService.addProduct(product);

        // build response
        String responseBody = messageBuilderService.buildResponseBody(responseProduct, returnCode);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/product/get-all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/get/{id}")
    public Integer getProductQuantityById(@PathVariable("id") Long id) {
        return productService.getProductQuantityById(id);
    }

    @PutMapping("/product/update-quantity/{id}/{quantity}")
    public Product updateProduct(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        return productService.updateProductQuantityById(id, quantity);
    }

    @PostMapping("/product/order/{id}/{quantity}")
    public Integer orderProduct(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        return productService.executeProductOrder(id, quantity);
    }

}
