package com.reaulou.bvktechtest.controller;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.service.MessageBuildService;
import com.reaulou.bvktechtest.service.ProductService;
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
        InternalRequest internalRequest;
        try{
            internalRequest = messageBuildService.parseExternalRequest(body);
        }catch (Exception e){
            InternalResponse errorResponse = new InternalResponse();
            errorResponse.setReturnCode("70");
            String errorResponseBody = messageBuildService.buildResponseBody(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBody);
        }

        // service
        InternalResponse internalResponse = productService.addProduct(internalRequest);

        // build response
        String responseBody = messageBuildService.buildResponseBody(internalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/product/get-all")
    public ResponseEntity getAllProducts() {
        // service
        InternalResponse internalResponse = productService.getAllProducts();

        // build response
        List<Product> productList = internalResponse.getProductList();

        String responseBody = messageBuildService.buildResponseBody(productList, internalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/product/get/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Long id) {
        // parse request
        InternalRequest internalRequest = new InternalRequest();
        internalRequest.setId(id);

        // service
        InternalResponse internalResponse = productService.getProductById(internalRequest);

        // build response
        JSONObject responseProductJSON = null;
        if(internalResponse.getProductList() != null) {
            Product responseProduct = internalResponse.getProductList().get(0);
            responseProductJSON = new JSONObject(responseProduct);
        }

        String responseBody = messageBuildService.buildResponseBody(responseProductJSON,internalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/product/update")
    public ResponseEntity updateProduct(RequestEntity<String> request) {
        // parse request
        String body = request.getBody();
        InternalRequest internalRequest = messageBuildService.parseExternalRequest(body);

        // service
        InternalResponse internalResponse = productService.updateProductById(internalRequest);

        // build response
        String responseBody = messageBuildService.buildResponseBody(internalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/product/order")
    public ResponseEntity orderProduct(RequestEntity<String> request) {
        // parse request
        String body = request.getBody();
        InternalRequest internalRequest = messageBuildService.parseExternalRequest(body);

        // service
        InternalResponse internalResponse = productService.executeProductOrder(internalRequest);

        // build response
        String responseBody = messageBuildService.buildResponseBody(internalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
