package com.reaulou.bvktechtest.controller;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.service.MessageBuildService;
import com.reaulou.bvktechtest.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MessageBuildService messageBuildService;


    @RequestMapping("/product/add")
    public ResponseEntity addProduct(RequestEntity<String> request) {
        // parse request
        String body = request.getBody();
        Product requestProduct = messageBuildService.parseProduct(body);
        InternalRequest internalRequest = messageBuildService.parseExternalRequest(body);

        // service
        InternalResponse internalResponse = productService.addProduct(internalRequest);

        // build response
        Product responseProduct = internalResponse.getProduct();
        JSONObject responseProductJSON = new JSONObject(responseProduct);
        String responseBody = messageBuildService.buildResponseBody("product", responseProductJSON);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/product/get-all")
    public ResponseEntity getAllProducts() {
        // service
        List<Product> productList = productService.getAllProducts();

        // build response
        JSONArray productListJSON = new JSONArray(productList);
        String responseBody = messageBuildService.buildResponseBody("product_list", productListJSON);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/product/get/{id}")
    public ResponseEntity getProductQuantityById(@PathVariable("id") Long id) {
        // service
        Integer quantity = productService.getProductQuantityById(id);

        // build response
        JSONObject responseContentJSON = new JSONObject();
        responseContentJSON.put("id", id);
        responseContentJSON.put("quantity", quantity);
        String responseBody = messageBuildService.buildResponseBody(responseContentJSON);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/product/update-quantity")
    public ResponseEntity updateProduct(RequestEntity<String> request) {
        // parse request
        String body = request.getBody();
        JSONObject requestContent = messageBuildService.parseRequestMessage(body);

        long id = requestContent.getLong("id");
        Integer requestQuantity = requestContent.getInt("quantity");

        // service
        Product responseProduct = productService.updateProductQuantityById(id, requestQuantity);

        // build response
        JSONObject responseProductJSON = new JSONObject(responseProduct);
        String responseBody = messageBuildService.buildResponseBody("product", responseProductJSON);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/product/order")
    public ResponseEntity orderProduct(RequestEntity<String> request) {
        // parse request
        String body = request.getBody();
        JSONObject requestContent = messageBuildService.parseRequestMessage(body);

        long id = requestContent.getLong("id");
        Integer requestQuantity = requestContent.getInt("quantity");

        // service
        Integer responseQuantity = productService.executeProductOrder(id, requestQuantity);

        // build response
        JSONObject responseContentJSON = new JSONObject();
        responseContentJSON.put("id", id);
        responseContentJSON.put("remaining-quantity", responseQuantity);
        String responseBody = messageBuildService.buildResponseBody(responseContentJSON);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
